package com.androidarchitecture.domain.models

data class Result<out T>(val status: Int, val data: T?, val exception: Exception? = null) {

    object Status {
        const val SUCCESS = 1
        const val ERROR = 0
    }

    companion object {
        fun <T> success(data: T): Result<T> {
            return Result(
                Status.SUCCESS,
                data
            )
        }

        fun <T> error(exception: Exception): Result<T> {
            return Result(
                Status.ERROR,
                null,
                exception
            )
        }
    }
}
