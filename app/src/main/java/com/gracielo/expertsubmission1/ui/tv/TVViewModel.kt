package com.gracielo.expertsubmission1.ui.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.gracielo.core.domain.usecase.CatalogueUseCase

class TVViewModel(catalogueUseCase: CatalogueUseCase) : ViewModel() {
    val tv = catalogueUseCase.getAllTV().asLiveData()
}