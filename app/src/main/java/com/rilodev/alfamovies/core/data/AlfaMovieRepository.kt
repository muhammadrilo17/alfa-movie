package com.rilodev.alfamovies.core.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.rilodev.alfamovies.core.data.local.LocalDataSource
import com.rilodev.alfamovies.core.data.remote.RemoteDataSource
import com.rilodev.alfamovies.core.data.remote.mediator.MoviesMediator
import com.rilodev.alfamovies.core.data.remote.network.ApiResponse
import com.rilodev.alfamovies.core.domain.model.MovieInfoModel
import com.rilodev.alfamovies.core.domain.model.MovieModel
import com.rilodev.alfamovies.core.domain.model.MovieReviewModel
import com.rilodev.alfamovies.core.domain.model.MovieTrailerModel
import com.rilodev.alfamovies.core.domain.repository.IAlfaMoviesRepository
import com.rilodev.alfamovies.core.helpers.Constants
import com.rilodev.alfamovies.core.helpers.mapper.MovieInfoMapper
import com.rilodev.alfamovies.core.helpers.mapper.MovieMapper
import com.rilodev.alfamovies.core.helpers.mapper.MovieReviewMapper
import com.rilodev.alfamovies.core.helpers.mapper.MovieTrailerMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.lang.Exception

class AlfaMovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : IAlfaMoviesRepository {
    override fun getMovies(): Flow<PagingData<MovieModel>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = 1),
            remoteMediator = MoviesMediator(
                localDataSource,
                remoteDataSource,
            ),
            pagingSourceFactory = {
                localDataSource.getAllMovieWithGenre()
            },
        ).flow.map { pagingData ->
            pagingData.map {
                MovieMapper.entityToModel(it)

            }
        }
    }

    override fun getInfoMovie(id: Int): Flow<Resource<MovieInfoModel>> {
        return flow {
            when (val response = remoteDataSource.getInfoMovie(id).first()) {
                is ApiResponse.Success -> {
                    val movieInfoModel = MovieInfoMapper.responseToModel(response.data)
                    emit(Resource.Success(movieInfoModel))
                }

                is ApiResponse.Error -> {
                    emit(Resource.Error(response.errorMessage))
                }
            }
        }
    }

    override fun getReviewMovie(id: Int): Flow<Resource<List<MovieReviewModel>>> {
        return flow {
            try {
                when (val response = remoteDataSource.getReviewMovie(id).first()) {
                    is ApiResponse.Success -> {
                        val movieReviewModel =
                            response.data.map { MovieReviewMapper.responseToModel(it) }
                        emit(Resource.Success(movieReviewModel))
                    }

                    is ApiResponse.Error -> {
                        emit(Resource.Error(response.errorMessage))
                    }
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    override fun getTrailerMovie(id: Int): Flow<Resource<MovieTrailerModel>> {
        return flow {
            try {
                when (val response = remoteDataSource.getTrailerMovie(id).first()) {
                    is ApiResponse.Success -> {
                        if (response.data.isNotEmpty()) {
                            val movieInfoModel =
                                response.data.lastOrNull { it.type == Constants.TRAILER }

                            if (movieInfoModel != null) {
                                emit(
                                    Resource.Success(
                                        MovieTrailerMapper.responseToModel(
                                            movieInfoModel
                                        )
                                    )
                                )
                            } else {
                                emit(Resource.Success(MovieTrailerMapper.responseToModel(response.data.last())))
                            }
                        } else {
                            emit(Resource.Error("Data is Empty"))
                        }
                    }

                    is ApiResponse.Error -> {
                        emit(Resource.Error(response.errorMessage))
                    }
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }
}