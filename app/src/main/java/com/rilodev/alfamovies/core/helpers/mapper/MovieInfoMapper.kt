package com.rilodev.alfamovies.core.helpers.mapper

import com.rilodev.alfamovies.core.data.remote.response.movie.MovieInfoResponse
import com.rilodev.alfamovies.core.domain.model.GenreModel
import com.rilodev.alfamovies.core.domain.model.MovieInfoModel
import com.rilodev.alfamovies.core.domain.model.ProductionCompanyModel
import com.rilodev.alfamovies.core.domain.model.ProductionCountryModel
import com.rilodev.alfamovies.core.domain.model.SpokenLanguageModel

object MovieInfoMapper {
    fun responseToModel(response: MovieInfoResponse): MovieInfoModel {
        val genres = response.genres.map { GenreModel(it.id, it.name ?: "") }
        val productionCompanies = response.productionCompanies.map {
            ProductionCompanyModel(it.id, it.logoPath, it.name ?: "", it.originCountry ?: "")
        }
        val productionCountries = response.productionCountries.map {
            ProductionCountryModel(it.iso ?: "", it.name ?: "")
        }
        val spokenLanguages = response.spokenLanguages.map {
            SpokenLanguageModel(it.englishName ?: "", it.iso ?: "", it.name ?: "")
        }

        return MovieInfoModel(
            response.budget,
            genres,
            response.homepage ?: "",
            response.id,
            productionCompanies,
            productionCountries,
            response.revenue,
            response.runtime,
            spokenLanguages,
            response.status ?: "",
            response.tagline ?: ""
        )
    }
}