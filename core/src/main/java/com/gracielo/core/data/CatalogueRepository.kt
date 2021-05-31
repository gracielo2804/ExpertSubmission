package com.gracielo.core.data

import com.gracielo.core.data.source.remote.RemoteDataSource
import com.gracielo.core.data.source.remote.network.ApiResponses
import com.gracielo.core.data.source.remote.response.MoviesResponse
import com.gracielo.core.data.source.remote.response.TVResponse
import com.gracielo.core.domain.model.Movie
import com.gracielo.core.domain.model.TV
import com.gracielo.core.domain.repository.ICatalogueRepository
import com.gracielo.core.utils.AppExecutors
import com.gracielo.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CatalogueRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: com.gracielo.core.data.source.local.LocalDataSource,
    private val appExecutors: AppExecutors
) : ICatalogueRepository {

//    companion object {
//        @Volatile
//        private var instance: CatalogueRepository? = null
//
//        fun getInstance(
//            remoteData: RemoteDataSource,
//            localData: LocalDataSource,
//            appExecutors: AppExecutors
//        ): CatalogueRepository =
//            instance ?: synchronized(this) {
//                instance ?: CatalogueRepository(remoteData, localData, appExecutors)
//            }
//    }

    override fun getMovies(): Flow<com.gracielo.core.data.Resource<List<Movie>>> =
        object : com.gracielo.core.data.NetworkBoundResource<List<Movie>, List<MoviesResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map{
                    DataMapper.mapEntitiesToDomainMovie(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()
//                true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponses<List<MoviesResponse>>> =
                remoteDataSource.getAllMovie()

            override suspend fun saveCallResult(data: List<MoviesResponse>) {
                val movieList = DataMapper.mapResponsesToEntitiesMovies(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getTV(): Flow<com.gracielo.core.data.Resource<List<TV>>> =
        object : com.gracielo.core.data.NetworkBoundResource<List<TV>, List<TVResponse>>(){
            override fun loadFromDB(): Flow<List<TV>> {
                return localDataSource.getAllTV().map{
                    DataMapper.mapEntitiesToDomainTV(it)
                }
            }

            override fun shouldFetch(data: List<TV>?): Boolean =
                data == null || data.isEmpty()
//                true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponses<List<TVResponse>>> =
                remoteDataSource.getAllTV()

            override suspend fun saveCallResult(data: List<TVResponse>) {
                val TVList = DataMapper.mapResponsesToEntitiesTV(data)
                localDataSource.insertTV(TVList)
            }
        }.asFlow()

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map{
            DataMapper.mapEntitiesToDomainMovie(it)
        }
    }

    override fun getFavoriteTV(): Flow<List<TV>> {
        return localDataSource.getFavoriteTV().map {
            DataMapper.mapEntitiesToDomainTV(it)
        }
    }

    override fun setFavoriteMovies(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntityMovie(movie)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovie(movieEntity, state)
        }
    }

    override fun setFavoriteTV(tv: TV, state: Boolean) {
        val tvEntity = DataMapper.mapDomainToEntityTV(tv)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTV(tvEntity, state) }
    }

}