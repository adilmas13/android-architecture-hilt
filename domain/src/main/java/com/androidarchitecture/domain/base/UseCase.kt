package com.androidarchitecture.domain.base

import com.androidarchitecture.domain.models.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
