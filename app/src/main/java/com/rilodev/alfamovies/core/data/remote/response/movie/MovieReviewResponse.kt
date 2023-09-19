package com.rilodev.alfamovies.core.data.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MovieReviewResponse(
    @SerializedName("author") val author: String?,
    @SerializedName("author_details") val authorDetails: AuthorDetailsResponse,
    @SerializedName("content") val content: String?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("id") val id: String?,
    @SerializedName("updated_at") val updatedAt: String?,
    @SerializedName("url") val url: String?
)

data class AuthorDetailsResponse(
    @SerializedName("name") val name: String?,
    @SerializedName("username") val username: String?,
    @SerializedName("avatar_path") val avatarPath: String?,
    @SerializedName("rating") val rating: Int
)





