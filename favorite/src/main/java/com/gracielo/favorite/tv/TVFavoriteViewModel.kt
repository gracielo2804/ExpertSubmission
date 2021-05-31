package com.gracielo.favorite.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.gracielo.core.domain.usecase.CatalogueUseCase

class TVFavoriteViewModel(catalogueUseCase: CatalogueUseCase) : ViewModel() {

    val favTV = catalogueUseCase.getFavoriteTV().asLiveData()
}