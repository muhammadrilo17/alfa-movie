package com.rilodev.alfamovies.pages.info_movie.production

import androidx.lifecycle.asFlow
import com.rilodev.alfamovies.core.data.Resource
import com.rilodev.alfamovies.core.domain.model.MovieInfoModel
import com.rilodev.alfamovies.core.domain.repository.IAlfaMoviesRepository
import com.rilodev.alfamovies.core.utils.DataDummy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ProductionViewModelTest {

    @Mock
    private lateinit var repository: IAlfaMoviesRepository
    private lateinit var productionViewModel: ProductionViewModel
    private val dummy = DataDummy.generateDummyMoviesInfoModel()

    @Before
    fun setUp() {
        productionViewModel = ProductionViewModel(repository)
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
    fun `when Get Movie Info Model Should Not Null and Return Success`() {
        val expectedMovieInfo: Flow<Resource<MovieInfoModel>> = flow { Resource.Success(dummy) }

        `when`(repository.getInfoMovie(1)).thenReturn(expectedMovieInfo)
        val actualMovieInfo = productionViewModel.getInfoMovie(1).asFlow()
        assertNotNull(actualMovieInfo)
    }
}