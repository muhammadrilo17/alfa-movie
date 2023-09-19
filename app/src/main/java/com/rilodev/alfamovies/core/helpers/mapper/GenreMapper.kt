package com.rilodev.alfamovies.core.helpers.mapper

import com.rilodev.alfamovies.core.data.local.entity.GenreEntity
import com.rilodev.alfamovies.core.data.remote.response.movie.GenreResponse

object GenreMapper {
    fun responseToEntity(response: GenreResponse): GenreEntity {
        return GenreEntity(
            id = response.id,
            name = response.name ?: "",
        )
    }
}