package com.androidarchitecture.domain.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class UseCase<T> {

    abstract suspend fun makeRequest(): Flow<T>

    // handle any common logic here before making the async call for ex: checking for token expiry
    suspend fun execute(): Flow<T> {
        return makeRequest().flowOn(Dispatchers.IO)
    }
}
