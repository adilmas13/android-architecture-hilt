package com.shaikh.androidarchitecture.data.repository.base

import com.google.gson.Gson
import com.shaikh.androidarchitecture.data.retrofit.ApiService
import com.shaikh.androidarchitecture.data.retrofit.ApiServiceBuilder
import com.shaikh.androidarchitecture.domain.entities.ApiResponseWrapper
import com.shaikh.androidarchitecture.domain.entities.Result
import com.shaikh.androidarchitecture.domain.exceptions.ApiException
import retrofit2.Response

open class BaseRepository {

    open var api: ApiService = ApiServiceBuilder.service

    internal fun <T, R> parseResult(response: Response<T>, parser: (T) -> R): Result<R> {
        return if (response.isSuccessful && response.body() !== null)
            Result.success(parser.invoke(response.body()!!))
        else
            Result.error(getError(response))
    }

    private fun <T> getError(response: Response<T>): ApiException {
        val errorString = response.errorBody()?.toString() ?: ""
        val errorObject = try {
            Gson().fromJson(errorString, ApiResponseWrapper::class.java)
        } catch (e: Exception) {
            ApiResponseWrapper<Any>(
                status = false,
                error = "Something went wrong"
            )
        }
        return ApiException(errorObject.error)
    }
}