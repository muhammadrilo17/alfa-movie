package com.rilodev.alfamovies.core.data.local

import androidx.paging.PagingSource
import com.rilodev.alfamovies.core.data.local.entity.GenreEntity
import com.rilodev.alfamovies.core.data.local.entity.MovieEntity
import com.rilodev.alfamovies.core.data.local.entity.MovieGenreCrossRef
import com.rilodev.alfamovies.core.data.local.entity.MovieWithGenre
import com.rilodev.alfamovies.core.data.local.room.AlfaMovieDatabase
import com.rilodev.alfamovies.core.data.local.room.key.RemoteKeys

class LocalDataSource(val database: AlfaMovieDatabase) {
    fun getMovies(): PagingSource<Int, MovieEntity> {
        return database.alfaMovieDao().getMovies()
    }

    suspend fun insertMovies(data: List<MovieEntity>) {
        return database.alfaMovieDao().insertMovies(data)
    }

    suspend fun insertMovieGenreCrossRef(data: List<MovieGenreCrossRef>) {
        return database.alfaMovieDao().insertMovieGenreCrossRef(data)
    }

    suspend fun removeMovies() {
        return database.alfaMovieDao().deleteAll()
    }

    fun getAllMovieWithGenre(): PagingSource<Int, MovieWithGenre> {
        return database.alfaMovieDao().getAllMovieWithGenre()
    }

    suspend fun insertRemoteKeys(data: List<RemoteKeys>) {
        return database.remoteKeysDao().insertRemoteKeys(data)
    }

    suspend fun getRemoteKeysId(id: Int): RemoteKeys? {
        return database.remoteKeysDao().getRemoteKeysId(id)
    }

    suspend fun deleteRemoteKeys() {
        return database.remoteKeysDao().deleteRemoteKeys()
    }

    suspend fun insertGenres(data: List<GenreEntity>) {
        return database.alfaMovieDao().insertGenres(data)
    }

    suspend fun getGenresSize(): Int {
        return database.alfaMovieDao().getGenresSize()
    }

    suspend fun getAllGenres(): List<GenreEntity> {
        return database.alfaMovieDao().getAllGenres()
    }

    suspend fun getRemoteKeysForLastItem(): RemoteKeys? {
        return database.remoteKeysDao().getRemoteKeysForLastItem()
    }
}