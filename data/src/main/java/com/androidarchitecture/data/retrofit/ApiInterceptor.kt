package com.androidarchitecture.data.retrofit

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ApiInterceptor : Interceptor {

    companion object {
        private const val HEADER_CONTENT_TYPE = "Content-Type"
        private const val CONTENT_TYPE_JSON = "application/json"
        private const val HEADER_AUTHORIZATION = "Authorization"
        // token expires in Nov 05 2018
        private const val TOKEN =
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImFkaWwuc2hhaWtoQHJlcHVibGljd29ybGQuY29tIiwiaWF0IjoxNTYzMjczMDI1LCJpc3MiOiJodHRwOi8vbmV3YXBpLWRldi5yZXB1YmxpY2luZGlhLmNvbS8iLCJleHAiOjE1NjU4NjUwMjUsIm5iZiI6MTU2MzI3MzAyNX0.9U-fhVMznfHZG6AG4jYjMqEvm74jfSLNKFIuxyjef6s"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = addNecessaryHeaders(chain.request())
        return chain.proceed(request)
    }

    private fun addNecessaryHeaders(request: Request): Request {
        val newBuilder = request.newBuilder()
        newBuilder.header(
            HEADER_CONTENT_TYPE,
            CONTENT_TYPE_JSON
        )
        newBuilder.header(
            HEADER_AUTHORIZATION,
            TOKEN
        )
        return newBuilder.build()
    }
}
