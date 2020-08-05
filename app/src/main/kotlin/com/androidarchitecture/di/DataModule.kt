package com.androidarchitecture.di

import com.androidarchitecture.data.repository.RetrofitUsersRepository
import com.androidarchitecture.domain.NetworkMonitor
import com.androidarchitecture.domain.repository.UserRepository
import com.androidarchitecture.utilities.LiveConnectivityMonitor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideUserRepository(networkMonitor: NetworkMonitor): UserRepository =
        RetrofitUsersRepository(networkMonitor)

    @Provides
    @Singleton
    fun provideNetworkMonitor(): NetworkMonitor = LiveConnectivityMonitor()
}