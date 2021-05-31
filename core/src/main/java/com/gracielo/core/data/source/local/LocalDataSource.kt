package com.gracielo.core.data.source.local

import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val catalogueDao: com.gracielo.core.data.source.local.room.CatalogueDao) {

//    companion object {
//        private var instance: LocalDataSource? = null
//
//        fun getInstance(catalogueDao: CatalogueDao): LocalDataSource =
//            instance ?: synchronized(this) {
//                instance ?: LocalDataSource(catalogueDao)
//            }
//    }

    fun getAllMovie(): Flow<List<com.gracielo.core.data.source.local.entity.MovieEntity>> = catalogueDao.getAllMovie()
    fun getAllTV(): Flow<List<com.gracielo.core.data.source.local.entity.TVEntity>> = catalogueDao.getAllTV()

    fun getFavoriteMovie(): Flow<List<com.gracielo.core.data.source.local.entity.MovieEntity>> = catalogueDao.getFavoriteMovie()
    fun getFavoriteTV(): Flow<List<com.gracielo.core.data.source.local.entity.TVEntity>> = catalogueDao.getFavoriteTV()

    suspend fun insertMovie(movieList: List<com.gracielo.core.data.source.local.entity.MovieEntity>) = catalogueDao.insertMovie(movieList)
    suspend fun insertTV(tvList: List<com.gracielo.core.data.source.local.entity.TVEntity>) = catalogueDao.insertTV(tvList)

    fun setFavoriteMovie(movie: com.gracielo.core.data.source.local.entity.MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        catalogueDao.updateFavoriteMovie(movie)
    }

    fun setFavoriteTV(tv: com.gracielo.core.data.source.local.entity.TVEntity, newState: Boolean) {
        tv.isFavorite = newState
        catalogueDao.updateFavoriteTV(tv)
    }

}

