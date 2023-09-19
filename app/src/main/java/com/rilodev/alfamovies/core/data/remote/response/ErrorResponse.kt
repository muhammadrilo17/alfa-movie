package com.rilodev.alfamovies.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status_code")
    var statusCode: Int?,
    @SerializedName("status_message")
    var message: String?,
    @SerializedName("success")
    var succes: Boolean
)