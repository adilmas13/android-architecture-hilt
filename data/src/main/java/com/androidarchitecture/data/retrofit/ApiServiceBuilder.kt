package com.androidarchitecture.data.retrofit

import com.androidarchitecture.data.BuildConfig
import com.androidarchitecture.domain.NetworkMonitor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ApiServiceBuilder @Inject constructor(
    private val networkMonitor: NetworkMonitor,
    private val baseUrl: String
) {

    companion object {
        private const val CONNECT_TIMEOUT_SECONDS = 30L
        private const val READ_TIMEOUT_SECONDS = 30L
        private val contentType = "application/json".toMediaType()
    }

    @ExperimentalSerializationApi
    fun build(): ApiService {
        val httpInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }
        val builder = OkHttpClient.Builder()
            .addInterceptor(httpInterceptor)
            .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(ApiInterceptor())
            .addInterceptor(InternetConnectivityInterceptor(networkMonitor))
        val json = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .client(builder.build())
            .baseUrl(baseUrl)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(ApiService::class.java)
    }
}
