package com.rilodev.alfamovies.core.data.remote

import com.google.gson.Gson
import com.rilodev.alfamovies.core.data.remote.network.ApiResponse
import com.rilodev.alfamovies.core.data.remote.network.ApiService
import com.rilodev.alfamovies.core.data.remote.response.ErrorResponse
import com.rilodev.alfamovies.core.data.remote.response.movie.GenreResponse
import com.rilodev.alfamovies.core.data.remote.response.movie.MovieInfoResponse
import com.rilodev.alfamovies.core.data.remote.response.movie.MovieResponse
import com.rilodev.alfamovies.core.data.remote.response.movie.MovieReviewResponse
import com.rilodev.alfamovies.core.data.remote.response.movie.MovieTrailerResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getMovies(page: Int): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getPopularMovies(page)
                emit(ApiResponse.Success(response.results))
            } catch (e: HttpException) {
                val result =
                    Gson().fromJson(e.response()?.errorBody()?.string(), ErrorResponse::class.java)

                emit(ApiResponse.Error(result?.message.toString()))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getInfoMovie(id: Int): Flow<ApiResponse<MovieInfoResponse>> {
        return flow {
            try {
                val response = apiService.getInfoMovies(id)
                emit(ApiResponse.Success(response))
            } catch (e: HttpException) {
                val result =
                    Gson().fromJson(e.response()?.errorBody()?.string(), ErrorResponse::class.java)

                emit(ApiResponse.Error(result?.message.toString()))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTrailerMovie(id: Int): Flow<ApiResponse<List<MovieTrailerResponse>>> {
        return flow {
            try {
                val response = apiService.getTrailerMovie(id)
                emit(ApiResponse.Success(response.results))
            } catch (e: HttpException) {
                val result =
                    Gson().fromJson(e.response()?.errorBody()?.string(), ErrorResponse::class.java)

                emit(ApiResponse.Error(result?.message.toString()))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getReviewMovie(id: Int): Flow<ApiResponse<List<MovieReviewResponse>>> {
        return flow {
            try {
                val response = apiService.getReviewMovie(id)
                emit(ApiResponse.Success(response.results))
            } catch (e: HttpException) {
                val result =
                    Gson().fromJson(e.response()?.errorBody()?.string(), ErrorResponse::class.java)

                emit(ApiResponse.Error(result?.message.toString()))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMasterGenre(): Flow<ApiResponse<List<GenreResponse>>> {
        return flow {
            try {
                val response = apiService.getMasterGenre()
                emit(ApiResponse.Success(response.genres))
            } catch (e: HttpException) {
                val result =
                    Gson().fromJson(e.response()?.errorBody()?.string(), ErrorResponse::class.java)

                emit(ApiResponse.Error(result?.message.toString()))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}