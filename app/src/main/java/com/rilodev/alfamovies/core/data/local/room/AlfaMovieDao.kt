package com.rilodev.alfamovies.core.data.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.rilodev.alfamovies.core.data.local.entity.GenreEntity
import com.rilodev.alfamovies.core.data.local.entity.MovieEntity
import com.rilodev.alfamovies.core.data.local.entity.MovieGenreCrossRef
import com.rilodev.alfamovies.core.data.local.entity.MovieWithGenre
import com.rilodev.alfamovies.core.data.local.helpers.TableConstants.GENRE_TABLE
import com.rilodev.alfamovies.core.data.local.helpers.TableConstants.MOVIE_TABLE

@Dao
interface AlfaMovieDao {
    @Query("SELECT * FROM $MOVIE_TABLE")
    fun getMovies(): PagingSource<Int, MovieEntity>

    @Insert(onConflict = REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("DELETE FROM $MOVIE_TABLE")
    suspend fun deleteAll()

    @Transaction
    @Query("SELECT * from $MOVIE_TABLE")
    fun getAllMovieWithGenre(): PagingSource<Int, MovieWithGenre>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieGenreCrossRef(movieGenreCrossRef: List<MovieGenreCrossRef>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGenres(genres: List<GenreEntity>)

    @Query("SELECT COUNT(*) a FROM $GENRE_TABLE")
    suspend fun getGenresSize(): Int

    @Query("SELECT * FROM $GENRE_TABLE")
    suspend fun getAllGenres(): List<GenreEntity>
}