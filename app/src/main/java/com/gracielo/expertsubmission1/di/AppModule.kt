package com.gracielo.expertsubmission1.di

import com.gracielo.core.domain.usecase.CatalogueInteractor
import com.gracielo.core.domain.usecase.CatalogueUseCase
import com.gracielo.expertsubmission1.ui.detail.DetailViewModel
import com.gracielo.expertsubmission1.ui.movie.MovieViewModel
import com.gracielo.expertsubmission1.ui.tv.TVViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module{
    factory<CatalogueUseCase>{CatalogueInteractor(get())}
}

val viewModelModule = module{
    viewModel {MovieViewModel(get())}
    viewModel {TVViewModel(get())}
    viewModel {DetailViewModel(get())}

}