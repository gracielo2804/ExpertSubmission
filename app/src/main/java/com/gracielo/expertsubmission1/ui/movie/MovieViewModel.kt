package com.gracielo.expertsubmission1.ui.movie


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.gracielo.core.domain.usecase.CatalogueUseCase

class MovieViewModel(catalogueUseCase: CatalogueUseCase) : ViewModel() {
    val movie = catalogueUseCase.getAllMovie().asLiveData()
}