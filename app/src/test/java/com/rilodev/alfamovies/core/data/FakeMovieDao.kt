package com.rilodev.alfamovies.core.data

import androidx.paging.PagingSource
import com.rilodev.alfamovies.core.data.local.entity.GenreEntity
import com.rilodev.alfamovies.core.data.local.entity.MovieEntity
import com.rilodev.alfamovies.core.data.local.entity.MovieGenreCrossRef
import com.rilodev.alfamovies.core.data.local.entity.MovieWithGenre
import com.rilodev.alfamovies.core.data.local.room.AlfaMovieDao

class FakeMovieDao: AlfaMovieDao {
    private var movieData = mutableListOf<MovieEntity>()

    override fun getMovies(): PagingSource<Int, MovieEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun insertMovies(movies: List<MovieEntity>) {
        movieData.addAll(movies)
    }

    override suspend fun deleteAll() {
    }

    override fun getAllMovieWithGenre(): PagingSource<Int, MovieWithGenre> {
        TODO("Not yet implemented")
    }

    override suspend fun insertMovieGenreCrossRef(movieGenreCrossRef: List<MovieGenreCrossRef>) {
        TODO("Not yet implemented")
    }

    override suspend fun insertGenres(genres: List<GenreEntity>) {
        TODO("Not yet implemented")
    }

    override suspend fun getGenresSize(): Int {
        TODO("Not yet implemented")
    }

    override suspend fun getAllGenres(): List<GenreEntity> {
        TODO("Not yet implemented")
    }
}