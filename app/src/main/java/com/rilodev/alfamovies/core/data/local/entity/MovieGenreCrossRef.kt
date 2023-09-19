package com.rilodev.alfamovies.core.data.local.entity

import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.Entity
import com.rilodev.alfamovies.core.data.local.helpers.TableConstants.MOVIE_GENRE_CROSS_REF

@Entity(tableName = MOVIE_GENRE_CROSS_REF, primaryKeys = ["movie_id", "genre_id"])
data class MovieGenreCrossRef(
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
    @ColumnInfo(name = "genre_id", index = true)
    val genre: Int
)