package com.rilodev.alfamovies.core.helpers.mapper

import com.rilodev.alfamovies.core.data.remote.response.movie.MovieReviewResponse
import com.rilodev.alfamovies.core.domain.model.AuthorDetailsModel
import com.rilodev.alfamovies.core.domain.model.MovieReviewModel

object MovieReviewMapper {
    fun responseToModel(response: MovieReviewResponse): MovieReviewModel {
        return MovieReviewModel(
            id = response.id,
            author = response.author ?: "",
            content = response.content ?: "",
            createdAt = response.createdAt ?: "",
            updatedAt = response.updatedAt ?: "",
            url = response.url ?: "",
            authorDetails = AuthorDetailsModel(
                name = response.authorDetails.name ?: "",
                username = response.authorDetails.username ?: "",
                avatarPath = response.authorDetails.avatarPath ?: "",
                rating = response.authorDetails.rating
            )
        )
    }

}