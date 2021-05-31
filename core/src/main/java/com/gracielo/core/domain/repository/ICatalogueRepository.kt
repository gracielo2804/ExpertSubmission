package com.gracielo.core.domain.repository

import com.gracielo.core.domain.model.Movie
import com.gracielo.core.domain.model.TV
import kotlinx.coroutines.flow.Flow

interface ICatalogueRepository {


    fun getMovies(): Flow<com.gracielo.core.data.Resource<List<Movie>>>
    fun getTV(): Flow<com.gracielo.core.data.Resource<List<TV>>>

    fun getFavoriteMovies(): Flow<List<Movie>>
    fun getFavoriteTV(): Flow<List<TV>>

    fun setFavoriteMovies(movie: Movie, state: Boolean)
    fun setFavoriteTV(tv: TV, state: Boolean)
}