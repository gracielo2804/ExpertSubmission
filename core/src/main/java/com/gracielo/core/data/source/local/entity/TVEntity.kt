package com.gracielo.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tv")
data class TVEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "first_air_date")
    val firstAirDate: String? = null,

    @ColumnInfo(name = "overview")
    val overview: String? = null,

    @ColumnInfo(name = "original_language")
    val originalLanguage: String? = null,

    @ColumnInfo(name = "genre_ids")
    val genreIds: String? = null,

    @ColumnInfo(name = "poster_path")
    val posterPath: String? = null,

    @ColumnInfo(name = "origin_country")
    val originCountry: String? = null,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String? = null,

    @ColumnInfo(name = "original_name")
    val originalName: String? = null,

    @ColumnInfo(name = "popularity")
    val popularity: Double? = null,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double? = null,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "vote_count")
    val voteCount: Int? = null,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false

)
