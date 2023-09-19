package com.rilodev.alfamovies.pages.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import com.rilodev.alfamovies.core.domain.model.MovieModel
import com.rilodev.alfamovies.core.domain.repository.IAlfaMoviesRepository
import com.rilodev.alfamovies.core.ui.adapter.MoviesPagingAdapter
import com.rilodev.alfamovies.core.utils.DataDummy
import com.rilodev.alfamovies.core.utils.getOrAwaitValue
import com.rilodev.alfamovies.core.utils.listUpdateCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: IAlfaMoviesRepository
    private lateinit var homeViewModel: HomeViewModel
    private val dummy = DataDummy.generateDummyMoviesModel()

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(repository)
    }

    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setupDispatcher() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDownDispatcher() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when get movie list should not null and return success`() = runTest {
        val expectedMovie: Flow<PagingData<MovieModel>> = flow {
            emit(PagingData.from(dummy))
        }

        Mockito.`when`(repository.getMovies()).thenReturn(expectedMovie)

        val actualMovie = homeViewModel.getMovies().getOrAwaitValue()
        Mockito.verify(repository).getMovies()

        val differ = AsyncPagingDataDiffer(
            diffCallback = MoviesPagingAdapter.DIFF_CALLBACK,
            updateCallback = listUpdateCallback,
            workerDispatcher = Dispatchers.Main
        )
        differ.submitData(actualMovie)
        assertNotNull(differ.snapshot())
        assertEquals(dummy.size, differ.snapshot().size)
        assertEquals(dummy[0].id, differ.snapshot()[0]?.id)
    }
}