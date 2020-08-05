package com.androidarchitecture.di

import com.androidarchitecture.helpers.CoilImageLoader
import com.androidarchitecture.utilities.ImageLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideImageLoader(): ImageLoader =
        CoilImageLoader()
}
