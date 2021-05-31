package com.gracielo.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TV(
    val id: Int? = null,
    val firstAirDate: String? = null,
    val overview: String? = null,
    val originalLanguage: String? = null,
    val genreIds: String? = null,
    val posterPath: String? = null,
    val originCountry: String? = null,
    val backdropPath: String? = null,
    val originalName: String? = null,
    val popularity: Double? = null,
    val voteAverage: Double? = null,
    val name: String? = null,
    val voteCount: Int? = null,
    var isFavorite: Boolean = false
) : Parcelable