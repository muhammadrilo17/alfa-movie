package com.rilodev.alfamovies.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rilodev.alfamovies.core.domain.repository.IAlfaMoviesRepository
import com.rilodev.alfamovies.core.injection.Injection
import com.rilodev.alfamovies.pages.home.HomeViewModel
import com.rilodev.alfamovies.pages.info_movie.InfoViewModel
import com.rilodev.alfamovies.pages.info_movie.production.ProductionViewModel
import com.rilodev.alfamovies.pages.info_movie.review.ReviewViewModel

class ViewModelFactory private constructor(private val repository: IAlfaMoviesRepository) :
    ViewModelProvider.Factory {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(Injection.provideAlfaMovieRepository(context))
        }.also { instance = it }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                modelClass.run { getConstructor(IAlfaMoviesRepository::class.java).newInstance(repository) }
            }
            modelClass.isAssignableFrom(InfoViewModel::class.java) -> {
                modelClass.run { getConstructor(IAlfaMoviesRepository::class.java).newInstance(repository) }
            }
            modelClass.isAssignableFrom(ProductionViewModel::class.java) -> {
                modelClass.run { getConstructor(IAlfaMoviesRepository::class.java).newInstance(repository) }
            }
            modelClass.isAssignableFrom(ReviewViewModel::class.java) -> {
                modelClass.run { getConstructor(IAlfaMoviesRepository::class.java).newInstance(repository) }
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}