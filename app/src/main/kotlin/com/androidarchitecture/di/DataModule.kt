package com.androidarchitecture.di

import android.content.Context
import com.androidarchitecture.BuildConfig
import com.androidarchitecture.data.repository.RetrofitUsersRepository
import com.androidarchitecture.data.retrofit.ApiServiceBuilder
import com.androidarchitecture.domain.NetworkMonitor
import com.androidarchitecture.domain.repository.UserRepository
import com.androidarchitecture.helpers.LiveConnectivityMonitor
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(repository: RetrofitUsersRepository): UserRepository
}

@Module
@InstallIn(ApplicationComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideNetworkMonitor(@ApplicationContext context: Context): NetworkMonitor =
        LiveConnectivityMonitor(context)

    @Provides
    @Singleton
    fun provideRetrofitApiService(networkMonitor: NetworkMonitor, @BaseUrl baseUrl: String) =
        ApiServiceBuilder(networkMonitor, baseUrl).build()

    @Provides
    @Singleton
    @BaseUrl
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class BaseUrl
}
