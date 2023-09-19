package com.rilodev.alfamovies.core.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rilodev.alfamovies.core.data.local.entity.GenreEntity
import com.rilodev.alfamovies.core.data.local.entity.MovieEntity
import com.rilodev.alfamovies.core.data.local.entity.MovieGenreCrossRef
import com.rilodev.alfamovies.core.data.local.room.key.RemoteKeys
import com.rilodev.alfamovies.core.data.local.room.key.RemoteKeysDao

@Database(entities = [RemoteKeys::class, MovieEntity::class, GenreEntity::class, MovieGenreCrossRef::class], version = 1)
abstract class AlfaMovieDatabase : RoomDatabase() {
    abstract fun alfaMovieDao(): AlfaMovieDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object{
        @Volatile
        private var INSTANCE : AlfaMovieDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): AlfaMovieDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AlfaMovieDatabase::class.java, "alfamovie.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}