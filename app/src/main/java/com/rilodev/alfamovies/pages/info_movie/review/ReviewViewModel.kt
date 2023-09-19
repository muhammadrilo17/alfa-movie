package com.rilodev.alfamovies.pages.info_movie.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rilodev.alfamovies.core.data.Resource
import com.rilodev.alfamovies.core.domain.model.MovieReviewModel
import com.rilodev.alfamovies.core.domain.repository.IAlfaMoviesRepository

class ReviewViewModel(private val repository: IAlfaMoviesRepository): ViewModel() {
    fun getReviewMovie(id: Int): LiveData<Resource<List<MovieReviewModel>>> {
        return repository.getReviewMovie(id).asLiveData()
    }
}