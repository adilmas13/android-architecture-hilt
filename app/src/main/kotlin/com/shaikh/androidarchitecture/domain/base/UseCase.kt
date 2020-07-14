package com.shaikh.androidarchitecture.domain.base

import com.shaikh.androidarchitecture.domain.entities.Result
import kotlinx.coroutines.*

abstract class UseCase<T> {

    abstract suspend fun makeRequest(): Result<T>

    private var job: Job? = null

    fun execute(success: (T) -> Unit, failure: (Exception) -> Unit) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val result = try {
                makeRequest()
            } catch (e: Exception) {
                Result.error<T>(if (e.message.isNullOrEmpty()) Exception("Something went wrong") else e)
            }
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
