package com.rilodev.alfamovies.core.domain.model

data class MovieTrailerModel(
    val name: String,
    val key: String,
    val site: String,
    val type: String,
    val official: Boolean,
    val publishedAt: String,
    val id: String,
)