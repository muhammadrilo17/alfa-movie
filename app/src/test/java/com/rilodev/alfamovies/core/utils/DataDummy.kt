package com.rilodev.alfamovies.core.utils

import com.rilodev.alfamovies.core.domain.model.GenreModel
import com.rilodev.alfamovies.core.domain.model.MovieInfoModel
import com.rilodev.alfamovies.core.domain.model.MovieModel
import com.rilodev.alfamovies.core.domain.model.ProductionCompanyModel
import com.rilodev.alfamovies.core.domain.model.ProductionCountryModel
import com.rilodev.alfamovies.core.domain.model.SpokenLanguageModel

object DataDummy {
    fun generateDummyMoviesModel(): List<MovieModel> {
        val genres = List(10) { index ->
            GenreModel(index, "name$index")
        }

        val movies = List(10) { index ->
            MovieModel(index,
                "Title $index",
                "posterPath$index",
                index*100.0,
                index*23,
                "backdropPath$index",
                genres,
                "originalLang$index",
                "overview$index",
                index*11.0,
                "releaseDate$index",
                index % 2 == 0,
                index % 2 == 1)
        }

        return movies
    }

    fun generateDummyMoviesInfoModel(): List<MovieInfoModel> {
        val genres = List(10) { index ->
            GenreModel(index, "name$index")
        }

        val productionCompanies = List(11) { index ->
            ProductionCompanyModel(index, "logo$index", "name$index", "originCountry$index")
        }

        val productionCountries = List(12) { index ->
            ProductionCountryModel("iso$index", "name$index")
        }

        val spokenLanguages = List(12) { index ->
            SpokenLanguageModel("englishName$index", "iso$index", "name$index")
        }

        val movies = List(10) { index ->
            MovieInfoModel((index).toLong(),
                genres,
                "homePage$index",
                index,
                productionCompanies,
                productionCountries,
                index*7,
                index,
                spokenLanguages,
                "status$index",
                "releaseDate$index"
            )
        }

        return movies
    }
}