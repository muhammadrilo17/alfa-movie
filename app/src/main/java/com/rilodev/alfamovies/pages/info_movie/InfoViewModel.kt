package com.rilodev.alfamovies.pages.info_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rilodev.alfamovies.core.data.Resource
import com.rilodev.alfamovies.core.domain.model.MovieTrailerModel
import com.rilodev.alfamovies.core.domain.repository.IAlfaMoviesRepository

class InfoViewModel(private val repository: IAlfaMoviesRepository): ViewModel() {
    fun getTrailerMovie(id: Int): LiveData<Resource<MovieTrailerModel>> {
        return repository.getTrailerMovie(id).asLiveData()
    }
}