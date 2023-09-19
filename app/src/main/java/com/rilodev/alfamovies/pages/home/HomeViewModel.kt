package com.rilodev.alfamovies.pages.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.rilodev.alfamovies.core.domain.model.MovieModel
import com.rilodev.alfamovies.core.domain.repository.IAlfaMoviesRepository
import com.rilodev.alfamovies.core.helpers.wrapEspressoIdlingResource

class HomeViewModel(private val repository: IAlfaMoviesRepository) : ViewModel() {
    fun getMovies(): LiveData<PagingData<MovieModel>> {
        wrapEspressoIdlingResource {
            return repository.getMovies().asLiveData(viewModelScope.coroutineContext)
        }
    }
}