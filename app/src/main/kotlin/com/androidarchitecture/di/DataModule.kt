package com.androidarchitecture.di

import com.androidarchitecture.data.repository.RetrofitUsersRepository
import com.androidarchitecture.data.retrofit.ApiService
import com.androidarchitecture.data.retrofit.ApiServiceBuilder
import com.androidarchitecture.domain.NetworkMonitor
import com.androidarchitecture.domain.repository.UserRepository
import com.androidarchitecture.helpers.LiveConnectivityMonitor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideUserRepository(apiService: ApiService): UserRepository =
        RetrofitUsersRepository(apiService)

    @Provides
    @Singleton
    fun provideNetworkMonitor(): NetworkMonitor =
        LiveConnectivityMonitor()

    @Provides
    @Singleton
    fun provideRetrofitApiService(networkMonitor: NetworkMonitor, @BaseUrl baseUrl: String) =
        ApiServiceBuilder(networkMonitor, baseUrl).build()

    @Provides
    @Singleton
    @BaseUrl
    fun provideBaseUrl() = "https://reqres.in/"

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class BaseUrl
}