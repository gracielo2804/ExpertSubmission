package com.gracielo.core.data.source.remote

import android.util.Log
import com.gracielo.core.data.source.remote.network.ApiResponses
import com.gracielo.core.data.source.remote.network.ApiService
import com.gracielo.core.data.source.remote.response.MoviesResponse
import com.gracielo.core.data.source.remote.response.TVResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

//    companion object {
//        @Volatile
//        private var instance: RemoteDataSource? = null
//
//        fun getInstance(service: ApiService): RemoteDataSource =
//            instance ?: synchronized(this) {
//                instance ?: RemoteDataSource(service)
//            }
//    }

    suspend fun getAllMovie(): Flow<ApiResponses<List<MoviesResponse>>> {
        return flow{
            try {
                val response = apiService.getMovies()
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponses.Success(response.results))
                } else {
                    emit(ApiResponses.Empty)
                }
            }catch (e:Exception){
                emit(ApiResponses.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

//    fun getAllMovie(): LiveData<ApiResponses<List<MoviesResponse>>> {
//        val resultData = MutableLiveData<ApiResponses<List<MoviesResponse>>>()
//
//        //get data from remote api
//        val client = apiService.getMovies()
//
//        client.enqueue(object : Callback<ListMoviesResponse> {
//            override fun onResponse(
//                call: Call<ListMoviesResponse>,
//                response: Response<ListMoviesResponse>
//            ) {
//                Log.d("fragment", "Response : $response ")
//                Log.d("fragment", "Response Body : ${response.body()} ")
//                val dataArray = response.body()?.results
//                resultData.value =
//                    if (dataArray != null) ApiResponses.Success(dataArray) as ApiResponses<List<MoviesResponse>> else ApiResponses.Empty
//                Log.d("fragment", "DataArray : $dataArray ")
//            }
//
//            override fun onFailure(call: Call<ListMoviesResponse>, t: Throwable) {
//                resultData.value = ApiResponses.Error(t.message.toString())
//                Log.e("RemoteDataSource", t.message.toString())
//            }
//        })
//
//        return resultData
//    }

    suspend fun getAllTV(): Flow<ApiResponses<List<TVResponse>>> {
        return flow{
            try {
                val response = apiService.getTV()
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponses.Success(response.results))
                } else {
                    emit(ApiResponses.Empty)
                }
            }catch (e:Exception){
                emit(ApiResponses.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}