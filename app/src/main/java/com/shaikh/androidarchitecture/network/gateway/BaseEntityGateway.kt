package com.shaikh.androidarchitecture.network.gateway

import android.util.Log
import com.google.gson.Gson
import com.shaikh.androidarchitecture.ApiException
import com.shaikh.androidarchitecture.ApiResponseWrapper
import com.shaikh.androidarchitecture.Result
import com.shaikh.androidarchitecture.network.ApiService
import com.shaikh.androidarchitecture.network.ApiServiceBuilder
import retrofit2.Response

open class BaseEntityGateway {

    open var api: ApiService = ApiServiceBuilder.service

    internal fun <T, R> splitBodyAndError(response: Response<T>, parser: (T) -> R): Result<R> {
        val responseBody = response.body()
        return if (responseBody == null) {
            val errorString = response.errorBody()?.string()
            return try {
                val apiResponseWrapper =
                    Gson().fromJson(errorString, ApiResponseWrapper::class.java)
                Result.error(ApiException(apiResponseWrapper.error ?: errorString))
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