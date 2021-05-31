package com.gracielo.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [com.gracielo.core.data.source.local.entity.TVEntity::class, com.gracielo.core.data.source.local.entity.MovieEntity::class], version = 1, exportSchema = false)
abstract class CatalogueDatabase : RoomDatabase() {
    abstract fun catalogueDao(): com.gracielo.core.data.source.local.room.CatalogueDao

    companion object {
        @Volatile
        private var INSTANCE: com.gracielo.core.data.source.local.room.CatalogueDatabase? = null

        fun getInstance(context: Context): com.gracielo.core.data.source.local.room.CatalogueDatabase =
            com.gracielo.core.data.source.local.room.CatalogueDatabase.Companion.INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    com.gracielo.core.data.source.local.room.CatalogueDatabase::class.java,
                    "Catalogue.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                com.gracielo.core.data.source.local.room.CatalogueDatabase.Companion.INSTANCE = instance
                instance
            }
    }
}