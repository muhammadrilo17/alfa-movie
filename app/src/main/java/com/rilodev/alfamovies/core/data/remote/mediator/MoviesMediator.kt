package com.rilodev.alfamovies.core.data.remote.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.rilodev.alfamovies.core.data.local.LocalDataSource
import com.rilodev.alfamovies.core.data.local.entity.MovieWithGenre
import com.rilodev.alfamovies.core.data.local.entity.MovieGenreCrossRef
import com.rilodev.alfamovies.core.data.local.room.key.RemoteKeys
import com.rilodev.alfamovies.core.data.remote.RemoteDataSource
import com.rilodev.alfamovies.core.data.remote.network.ApiResponse
import com.rilodev.alfamovies.core.helpers.mapper.GenreMapper
import com.rilodev.alfamovies.core.helpers.mapper.MovieMapper
import kotlinx.coroutines.flow.first
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class MoviesMediator(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) : RemoteMediator<Int, MovieWithGenre>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieWithGenre>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: INITIAL_PAGE_INDEX
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem()
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }

        return try {
            val localGenres = localDataSource.getAllGenres()
            localGenres.ifEmpty {
                when (val response = remoteDataSource.getMasterGenre().first()) {
                    is ApiResponse.Success -> {
                        val data = response.data.map { GenreMapper.responseToEntity(it) }
                        localDataSource.insertGenres(data)
                        data
                    }
                    is ApiResponse.Error -> {
                        emptyList()
                    }
                }
            }
            when (val response = remoteDataSource.getMovies(page).first()) {
                is ApiResponse.Success -> {
                    val endOfPaginationReached = response.data.isEmpty()
                    val results = response.data.map { MovieMapper.responseToEntity(it) }
                    localDataSource.database.withTransaction {
                        if (loadType == LoadType.REFRESH) {
                            localDataSource.deleteRemoteKeys()
                            localDataSource.removeMovies()
                        }
                        val prevKey = if (page == 1) null else page - 1
                        val nextKey = if (endOfPaginationReached) null else page + 1
                        val keys = response.data.map {
                            RemoteKeys(movieFlag = it.id.toString(), prevKey = prevKey, nextKey = nextKey)
                        }
                        localDataSource.insertRemoteKeys(keys)
                        localDataSource.insertMovies(results)
                        val flattenedGenres = response.data.flatMap { movie ->
                            movie.genreIds.map { genre ->
                                MovieGenreCrossRef(movie.id, genre)
                            }
                        }
                        localDataSource.insertMovieGenreCrossRef(flattenedGenres)
                    }
                    MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
                }

                is ApiResponse.Error -> {
                    MediatorResult.Error(IOException(response.errorMessage))
                }
            }
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(): RemoteKeys? {
        return localDataSource.getRemoteKeysForLastItem()
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, MovieWithGenre>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let {
            localDataSource.getRemoteKeysId(it.movieEntity.id)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, MovieWithGenre>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.movieEntity?.id?.let {
                localDataSource.getRemoteKeysId(it)
            }
        }
    }

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
}