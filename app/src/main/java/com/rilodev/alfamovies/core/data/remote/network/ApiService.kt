package com.rilodev.alfamovies.core.data.remote.network

import com.rilodev.alfamovies.core.data.remote.response.BaseResponse
import com.rilodev.alfamovies.core.data.remote.response.movie.BaseGenreResponse
import com.rilodev.alfamovies.core.data.remote.response.movie.MovieInfoResponse
import com.rilodev.alfamovies.core.data.remote.response.movie.MovieResponse
import com.rilodev.alfamovies.core.data.remote.response.movie.MovieReviewResponse
import com.rilodev.alfamovies.core.data.remote.response.movie.MovieTrailerResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
    ): BaseResponse<List<MovieResponse>>

    @GET("movie/{movieId}")
    suspend fun getInfoMovies(
        @Path("movieId") id: Int,
    ): MovieInfoResponse

    @GET("movie/{movieId}/videos")
    suspend fun getTrailerMovie(
        @Path("movieId") id: Int,
    ): BaseResponse<List<MovieTrailerResponse>>

    @GET("movie/{movieId}/reviews")
    suspend fun getReviewMovie(
        @Path("movieId") id: Int,
    ): BaseResponse<List<MovieReviewResponse>>

    @GET("genre/movie/list")
    suspend fun getMasterGenre(): BaseGenreResponse
}