package com.rilodev.alfamovies.core.data.remote.response.movie

import com.google.gson.annotations.SerializedName

class MovieTrailerResponse (
    @SerializedName("name") val name: String?,
    @SerializedName("key") val key: String?,
    @SerializedName("site") val site: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("official") val official: Boolean,
    @SerializedName("published_at") val publishedAt: String?,
    @SerializedName("id") val id: String?,
)
