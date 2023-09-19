package com.rilodev.alfamovies.core.domain.model

import com.rilodev.alfamovies.core.helpers.MovieType

data class BadgeModel(
    val labelName: String,
    var isSelected: Boolean,
    val movieType: MovieType
)