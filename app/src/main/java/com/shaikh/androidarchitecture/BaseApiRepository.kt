package com.shaikh.androidarchitecture

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseApiRepository<T> {

    abstract suspend fun makeRequest(): Result<T>

    fun execute(success: (T) -> Unit, failure: (Exception) -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            val result = makeRequest()
            withContext(Dispatchers.Main) {
                when (result.status) {
                    Result.Status.SUCCESS -> success.invoke(result.data!!)
                    Result.Status.ERROR -> failure.invoke(
                        result.exception ?: Exception("Something went wrong")
                    )
                }
            }
        }
    }
}