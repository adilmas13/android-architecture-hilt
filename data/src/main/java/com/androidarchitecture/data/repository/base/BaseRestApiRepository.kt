package com.androidarchitecture.data.repository.base

import com.androidarchitecture.data.entities.ApiResponseWrapperEntity
import com.androidarchitecture.domain.exceptions.ApiException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import retrofit2.Response

open class BaseRestApiRepository {

    private val json = Json { ignoreUnknownKeys = true }

    internal fun <T, R> parseResult(response: Response<T>, parser: (T) -> R): Flow<R> {
        return flow {
            if (response.isSuccessful && response.body() !== null)
                emit(parser.invoke(response.body()!!))
            else
                throw getError(response)
        }
    }

    private fun <T> getError(response: Response<T>): ApiException {
        val errorString = response.errorBody()?.toString() ?: ""
        val errorObject = try {
            json.decodeFromString(errorString)
        } catch (e: Exception) {
            ApiResponseWrapperEntity<Any>(
                status = false,
                error = "Something went wrong"
            )
        }
        return ApiException(errorObject.error)
    }
}
