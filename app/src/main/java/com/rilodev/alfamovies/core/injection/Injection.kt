package com.rilodev.alfamovies.core.injection

import android.content.Context
import com.rilodev.alfamovies.core.data.AlfaMovieRepository
import com.rilodev.alfamovies.core.data.local.LocalDataSource
import com.rilodev.alfamovies.core.data.local.room.AlfaMovieDatabase
import com.rilodev.alfamovies.core.data.remote.RemoteDataSource
import com.rilodev.alfamovies.core.data.remote.network.ApiConfig

object Injection {
    fun provideAlfaMovieRepository(context: Context): AlfaMovieRepository {
        val remoteDataSource = RemoteDataSource(ApiConfig.getApiService())
        val database = AlfaMovieDatabase.getInstance(context)
        val localDataSource = LocalDataSource(database)

        return AlfaMovieRepository(remoteDataSource, localDataSource)
    }
}