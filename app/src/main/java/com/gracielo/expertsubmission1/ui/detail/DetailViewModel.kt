package com.gracielo.expertsubmission1.ui.detail

import androidx.lifecycle.ViewModel
import com.gracielo.core.domain.model.Movie
import com.gracielo.core.domain.model.TV
import com.gracielo.core.domain.usecase.CatalogueUseCase

class DetailViewModel(private val catalogueUseCase: CatalogueUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, newStatus: Boolean) =
        catalogueUseCase.setFavoriteMovie(movie, newStatus)

    fun setFavoriteTV(tv: TV, newStatus: Boolean) =
        catalogueUseCase.setFavoriteTV(tv, newStatus)
}