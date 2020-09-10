package com.androidarchitecture.data.repository.base

import com.androidarchitecture.data.entities.ApiResponseWrapperEntity
import com.androidarchitecture.domain.exceptions.ApiException
import com.androidarchitecture.domain.models.Result
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import retrofit2.Response

open class BaseRestApiRepository {

    private val json = Json { ignoreUnknownKeys = true }
    internal fun <T, R> parseResult(response: Response<T>, parser: (T) -> R): Result<R> {
        return if (response.isSuccessful && response.body() !== null)
            Result.success(parser.invoke(response.body()!!))
        else
            Result.error(getError(response))
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
