package com.rilodev.alfamovies.core.data.local.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MovieWithGenre (
    @Embedded
    val movieEntity: MovieEntity,

    @Relation(
        parentColumn = "id",
        entity = GenreEntity::class,
        entityColumn = "id",
        associateBy = Junction(
            value = MovieGenreCrossRef::class,
            parentColumn = "movie_id",
            entityColumn = "genre_id"
        )
    )
    val genre: List<GenreEntity>
)
