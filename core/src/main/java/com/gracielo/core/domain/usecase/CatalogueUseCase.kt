package com.gracielo.core.domain.usecase

import com.gracielo.core.domain.model.Movie
import com.gracielo.core.domain.model.TV
import kotlinx.coroutines.flow.Flow

interface CatalogueUseCase {
    fun getAllMovie(): Flow<com.gracielo.core.data.Resource<List<Movie>>>
    fun getAllTV(): Flow<com.gracielo.core.data.Resource<List<TV>>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun getFavoriteTV(): Flow<List<TV>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
    fun setFavoriteTV(tv: TV, state: Boolean)
}