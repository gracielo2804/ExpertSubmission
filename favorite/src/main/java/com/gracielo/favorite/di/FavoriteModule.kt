package com.gracielo.favorite.di

import com.gracielo.favorite.movie.MovieFavoriteViewModel
import com.gracielo.favorite.tv.TVFavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val FavoriteModule = module {
    viewModel { MovieFavoriteViewModel(get()) }
    viewModel { TVFavoriteViewModel(get()) }
}