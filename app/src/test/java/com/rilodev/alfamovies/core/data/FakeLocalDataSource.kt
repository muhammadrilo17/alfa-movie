package com.rilodev.alfamovies.core.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rilodev.alfamovies.core.data.local.LocalDataSource
import com.rilodev.alfamovies.core.data.remote.RemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
class FakeLocalDataSource {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

//    @get:Rule
//    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var localDataSource: LocalDataSource
    private lateinit var alfaMovieRepository: AlfaMovieRepository

    @Before
    fun setUp() {
//        remoteDataSource = RemoteDataSource()
//        localDataSource = LocalDataSource()
//        newsRepository = NewsRepository(apiService, newsDao)

    }

}