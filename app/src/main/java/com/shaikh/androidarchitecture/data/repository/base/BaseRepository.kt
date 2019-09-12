package com.shaikh.androidarchitecture.data.repository.base

import com.google.gson.Gson
import com.shaikh.androidarchitecture.domain.exceptions.ApiException
import com.shaikh.androidarchitecture.domain.entities.ApiResponseWrapper
import com.shaikh.androidarchitecture.domain.entities.Result
import com.shaikh.androidarchitecture.data.retrofit.ApiService
import com.shaikh.androidarchitecture.data.retrofit.ApiServiceBuilder
import retrofit2.Response

open class BaseRepository {

    open var api: ApiService = ApiServiceBuilder.service

    internal fun <T, R> parseResult(response: Response<T>, parser: (T) -> R): Result<R> {
        val responseBody = response.body()
        return if (responseBody == null) {
            val errorString = response.errorBody()?.string()
            return try {
                val apiResponseWrapper =
                    Gson().fromJson(errorString, ApiResponseWrapper::class.java)
                Result.error(
                    ApiException(
                        apiResponseWrapper.error ?: errorString
                    )
                )
            } catch (e: Exception) {
                Result.error(ApiException("Something went wrong"))
            }
        } else {
            return if (response.isSuccessful && response.body() !== null) {
                Result.success(parser.invoke(response.body()!!))
            } else {
                Result.error(ApiException("Hello"))
            }
        }
    }

}