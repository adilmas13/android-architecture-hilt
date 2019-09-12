package com.shaikh.androidarchitecture.domain.base

import com.shaikh.androidarchitecture.domain.entities.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class UseCase<T> {

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