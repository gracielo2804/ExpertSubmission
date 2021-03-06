package com.gracielo.core.data.source.remote.network

import com.gracielo.core.BuildConfig
import com.gracielo.core.data.source.remote.response.ListMoviesResponse
import com.gracielo.core.data.source.remote.response.ListTVResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("3/discover/movie")
    suspend fun getMovies(
        @Query("api_key") api_key: String = BuildConfig.api_key,
        @Query("language") language: String = "en-US",
        @Query("sort_by") sort_by: String = "popularity.desc",
        @Query("include_adult") include_adult: String = "false",
        @Query("vote_average.gte") param: Int = 8,
        @Query("release_date.gte") parameter: String = "2018-01-01"
    ): ListMoviesResponse

    @GET("3/discover/tv")
    suspend fun getTV(
        @Query("api_key") api_key: String = BuildConfig.api_key,
        @Query("language") language: String = "en-US",
        @Query("sort_by") sort_by: String = "popularity.desc",
        @Query("include_adult") include_adult: String = "false",
        @Query("vote_average.gte") param: Int = 8,
        @Query("release_date.gte") parameter: String = "2018-01-01"
        ): ListTVResponse
}