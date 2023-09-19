package com.rilodev.alfamovies.core.helpers.mapper

import com.rilodev.alfamovies.core.data.remote.response.movie.MovieTrailerResponse
import com.rilodev.alfamovies.core.domain.model.MovieTrailerModel

object MovieTrailerMapper {
    fun responseToModel(response: MovieTrailerResponse): MovieTrailerModel {
        return MovieTrailerModel(
            name = response.name ?: "",
            key = response.key ?: "",
            site = response.site ?: "",
            type = response.type ?: "",
            official = response.official,
            publishedAt = response.publishedAt ?: "",
            id = response.id ?: ""
        )
    }
}