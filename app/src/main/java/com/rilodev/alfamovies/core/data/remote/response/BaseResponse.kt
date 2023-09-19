package com.rilodev.alfamovies.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<TValue>(
    @SerializedName("page")
    var message: String?,
    @SerializedName("results")
    var results: TValue
)