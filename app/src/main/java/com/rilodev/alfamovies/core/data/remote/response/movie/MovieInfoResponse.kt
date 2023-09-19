package com.rilodev.alfamovies.core.data.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MovieInfoResponse(
    @SerializedName("budget")
    val budget: Long,

    @SerializedName("genres")
    val genres: List<GenreResponse>,

    @SerializedName("homepage")
    val homepage: String?,

    @SerializedName("id")
    val id: Int,

    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanyResponse>,

    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountryResponse>,

    @SerializedName("revenue")
    val revenue: Int,

    @SerializedName("runtime")
    val runtime: Int,

    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageResponse>,

    @SerializedName("status")
    val status: String?,

    @SerializedName("tagline")
    val tagline: String?
)

data class GenreResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String?
)

data class ProductionCompanyResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("logo_path")
    val logoPath: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("origin_country")
    val originCountry: String?
)

data class ProductionCountryResponse(
    @SerializedName("iso_3166_1")
    val iso: String?,

    @SerializedName("name")
    val name: String?
)

data class SpokenLanguageResponse(
    @SerializedName("english_name")
    val englishName: String?,

    @SerializedName("iso_639_1")
    val iso: String?,

    @SerializedName("name")
    val name: String?
)
