package com.gracielo.core.data.source.local.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CatalogueDao {

    @Query("SELECT * FROM movie")
    fun getAllMovie(): Flow<List<com.gracielo.core.data.source.local.entity.MovieEntity>>

    @Query("SELECT * FROM tv")
    fun getAllTV(): Flow<List<com.gracielo.core.data.source.local.entity.TVEntity>>

    @Query("SELECT * FROM movie where isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<com.gracielo.core.data.source.local.entity.MovieEntity>>

    @Query("SELECT * FROM tv where isFavorite = 1")
    fun getFavoriteTV(): Flow<List<com.gracielo.core.data.source.local.entity.TVEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<com.gracielo.core.data.source.local.entity.MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTV(tv: List<com.gracielo.core.data.source.local.entity.TVEntity>)

    @Update
    fun updateFavoriteMovie(movie: com.gracielo.core.data.source.local.entity.MovieEntity)

    @Update
    fun updateFavoriteTV(tv: com.gracielo.core.data.source.local.entity.TVEntity)
}