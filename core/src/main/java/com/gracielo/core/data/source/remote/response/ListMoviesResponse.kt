package com.gracielo.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMoviesResponse(

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("results")
    val results: List<MoviesResponse>,

    @field:SerializedName("total_results")
    val totalResults: Int
)
