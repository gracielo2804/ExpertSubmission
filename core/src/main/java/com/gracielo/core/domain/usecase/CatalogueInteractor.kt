package com.gracielo.core.domain.usecase

import com.gracielo.core.domain.model.Movie
import com.gracielo.core.domain.model.TV
import com.gracielo.core.domain.repository.ICatalogueRepository

class CatalogueInteractor(private val catalogueRepository: ICatalogueRepository) :
    CatalogueUseCase {
    override fun getAllMovie() = catalogueRepository.getMovies()

    override fun getAllTV() = catalogueRepository.getTV()

    override fun getFavoriteMovie() = catalogueRepository.getFavoriteMovies()

    override fun getFavoriteTV() = catalogueRepository.getFavoriteTV()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) =
        catalogueRepository.setFavoriteMovies(movie, state)

    override fun setFavoriteTV(tv: TV, state: Boolean) =
        catalogueRepository.setFavoriteTV(tv, state)


}