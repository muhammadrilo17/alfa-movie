package com.rilodev.alfamovies.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieModel(
    val id: Int,
    val title: String,
    val posterPath: String,
    val voteAverage: Double,
    val voteCount: Int,
    val backdropPath: String,
    val genreIds: List<GenreModel>,
    val originalLanguage: String,
    val overview: String,
    val popularity: Double,
    val releaseDate: String,
    val video: Boolean,
    val adult: Boolean
): Parcelable
