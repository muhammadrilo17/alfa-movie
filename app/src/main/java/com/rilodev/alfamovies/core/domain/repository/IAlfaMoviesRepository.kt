package com.rilodev.alfamovies.core.domain.repository

import androidx.paging.PagingData
import com.rilodev.alfamovies.core.data.Resource
import com.rilodev.alfamovies.core.domain.model.MovieInfoModel
import com.rilodev.alfamovies.core.domain.model.MovieModel
import com.rilodev.alfamovies.core.domain.model.MovieReviewModel
import com.rilodev.alfamovies.core.domain.model.MovieTrailerModel
import kotlinx.coroutines.flow.Flow

interface IAlfaMoviesRepository {
    fun getMovies(): Flow<PagingData<MovieModel>>

    fun getInfoMovie(id: Int): Flow<Resource<MovieInfoModel>>
    fun getReviewMovie(id: Int): Flow<Resource<List<MovieReviewModel>>>
    fun getTrailerMovie(id: Int): Flow<Resource<MovieTrailerModel>>
}