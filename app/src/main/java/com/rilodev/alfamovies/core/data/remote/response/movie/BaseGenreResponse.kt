package com.rilodev.alfamovies.core.data.remote.response.movie

import com.google.gson.annotations.SerializedName

data class BaseGenreResponse (
    @SerializedName("genres")
    val genres: List<GenreResponse>
)