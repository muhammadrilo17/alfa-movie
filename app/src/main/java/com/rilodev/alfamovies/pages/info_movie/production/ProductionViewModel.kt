package com.rilodev.alfamovies.pages.info_movie.production

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rilodev.alfamovies.core.data.Resource
import com.rilodev.alfamovies.core.domain.model.MovieInfoModel
import com.rilodev.alfamovies.core.domain.repository.IAlfaMoviesRepository

class ProductionViewModel(private val repository: IAlfaMoviesRepository): ViewModel() {
    fun getInfoMovie(id: Int): LiveData<Resource<MovieInfoModel>> {
        return repository.getInfoMovie(id).asLiveData()
    }
}