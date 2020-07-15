package com.androidarchitecture.data.retrofit

import com.androidarchitecture.domain.NetworkMonitor
import com.androidarchitecture.domain.exceptions.NoNetworkException
import okhttp3.Interceptor
import okhttp3.Response

class InternetConnectivityInterceptor(private val monitor: NetworkMonitor) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (monitor.isConnected()) {
            return chain.proceed(chain.request())
        }
        throw NoNetworkException()
    }
}
