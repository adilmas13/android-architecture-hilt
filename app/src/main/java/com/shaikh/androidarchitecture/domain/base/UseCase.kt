package com.shaikh.androidarchitecture.domain.base

import com.shaikh.androidarchitecture.domain.entities.Result
import kotlinx.coroutines.*

abstract class UseCase<T> {

    abstract suspend fun makeRequest(): Result<T>

    private var job: Job? = null

    fun execute(success: (T) -> Unit, failure: (Exception) -> Unit) {
        job = GlobalScope.launch {
            val result = withContext(Dispatchers.IO) { makeRequest() }
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

    fun cancel() = job?.cancel()
}