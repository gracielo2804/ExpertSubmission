package com.gracielo.core.data

import com.gracielo.core.data.source.remote.network.ApiResponses
import kotlinx.coroutines.flow.*

abstract class  NetworkBoundResource<ResultType, RequestType>{

    private var result : Flow<com.gracielo.core.data.Resource<ResultType>> = flow {
        emit(com.gracielo.core.data.Resource.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(com.gracielo.core.data.Resource.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponses.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map { com.gracielo.core.data.Resource.Success(it) })
                }
                is ApiResponses.Empty -> {
                    emitAll(loadFromDB().map { com.gracielo.core.data.Resource.Success(it) })
                }
                is ApiResponses.Error -> {
                    onFetchFailed()
                    emit(com.gracielo.core.data.Resource.Error<ResultType>(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadFromDB().map { com.gracielo.core.data.Resource.Success(it) })
        }
    }


    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponses<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)


    fun asFlow(): Flow<com.gracielo.core.data.Resource<ResultType>> = result
}