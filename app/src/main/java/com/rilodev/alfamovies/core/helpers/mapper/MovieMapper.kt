package com.rilodev.alfamovies.core.helpers.mapper

import com.rilodev.alfamovies.core.data.local.entity.MovieEntity
import com.rilodev.alfamovies.core.data.local.entity.MovieWithGenre
import com.rilodev.alfamovies.core.data.remote.response.movie.MovieResponse
import com.rilodev.alfamovies.core.domain.model.GenreModel
import com.rilodev.alfamovies.core.domain.model.MovieModel

object MovieMapper {
    fun entityToModel(entity: MovieWithGenre): MovieModel {
        val genres = entity.genre.map { GenreModel(it.id, it.name) }
        return MovieModel(
            id = entity.movieEntity.id,
            title = entity.movieEntity.title,
            posterPath = entity.movieEntity.posterPath,
            voteAverage = entity.movieEntity.voteAverage,
            voteCount = entity.movieEntity.voteCount,
            backdropPath = entity.movieEntity.backdropPath,
            genreIds = genres,
            originalLanguage = entity.movieEntity.originalLanguage,
            overview = entity.movieEntity.overview,
            popularity = entity.movieEntity.popularity,
            releaseDate = entity.movieEntity.releaseDate,
            video = entity.movieEntity.video,
            adult = entity.movieEntity.adult
        )
    }

    fun responseToEntity(response: MovieResponse): MovieEntity {
        return MovieEntity(
            id = response.id,
            title = response.title ?: "",
            posterPath = response.posterPath  ?: "",
            voteAverage = response.voteAverage,
            voteCount = response.voteCount,
            backdropPath = response.backdropPath ?: "",
            originalLanguage = response.originalLanguage ?: "",
            overview = response.overview ?: "",
            popularity = response.popularity,
            releaseDate = response.releaseDate ?: "",
            video = response.video,
            adult = response.adult
        )
    }
}
