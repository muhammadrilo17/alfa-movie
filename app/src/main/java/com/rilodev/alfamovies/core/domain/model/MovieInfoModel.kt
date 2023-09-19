package com.rilodev.alfamovies.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieInfoModel(
    val budget: Long,

    val genres: List<GenreModel>,

    val homepage: String?,

    val id: Int,

    val productionCompanies: List<ProductionCompanyModel>,

    val productionCountries: List<ProductionCountryModel>,

    val revenue: Int,

    val runtime: Int,

    val spokenLanguages: List<SpokenLanguageModel>,

    val status: String,

    val tagline: String?
) : Parcelable

@Parcelize
data class GenreModel(
    val id: Int,
    val name: String
): Parcelable

@Parcelize
data class ProductionCompanyModel(
    val id: Int,

    val logoPath: String?,

    val name: String,

    val originCountry: String
): Parcelable

@Parcelize
data class ProductionCountryModel(
    val iso: String,

    val name: String
): Parcelable

@Parcelize
data class SpokenLanguageModel(
    val englishName: String,

    val iso: String,

    val name: String
): Parcelable
