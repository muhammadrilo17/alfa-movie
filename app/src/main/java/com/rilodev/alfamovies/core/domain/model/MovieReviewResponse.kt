package com.rilodev.alfamovies.core.domain.model

data class MovieReviewModel(
    val author: String?,
    val authorDetails: AuthorDetailsModel,
    val content: String?,
    val createdAt: String?,
    val id: String?,
    val updatedAt: String?,
    val url: String?
)

data class AuthorDetailsModel(
    val name: String?,
    val username: String?,
    val avatarPath: String?,
    val rating: Int
)
