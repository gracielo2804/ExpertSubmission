package com.gracielo.expertsubmission1.ui.favorite.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.gracielo.core.domain.usecase.CatalogueUseCase

class MovieFavoriteViewModel(catalogueUseCase: CatalogueUseCase) : ViewModel() {

    val favMovie = catalogueUseCase.getFavoriteMovie().asLiveData()
}