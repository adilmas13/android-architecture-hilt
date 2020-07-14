package com.shaikh.androidarchitecture.data.retrofit

import com.google.gson.Gson
import com.shaikh.androidarchitecture.BuildConfig
import com.shaikh.androidarchitecture.data.interceptor.InternetConnectivityInterceptor
import com.shaikh.androidarchitecture.presentation.utilities.LiveConnectivityMonitor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiServiceBuilder {

    private const val CONNECT_TIMEOUT_SECONDS = 30L
    private const val READ_TIMEOUT_SECONDS = 30L
    private const val BASE_URL = "https://reqres.in/"
    val service: ApiService by lazy {
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
            .addInterceptor(InternetConnectivityInterceptor(LiveConnectivityMonitor()))

        return@lazy Retrofit.Builder()
            .client(builder.build())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(ApiService::class.java)
    }
}
