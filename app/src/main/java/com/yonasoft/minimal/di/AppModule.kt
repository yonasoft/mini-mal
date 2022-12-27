package com.yonasoft.minimal.di

import com.yonasoft.minimal.network.MALAuth
import com.yonasoft.minimal.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideAuth():MALAuth{
        return MALAuth()
    }

    @Provides
    @Singleton
    fun provideRepository():Repository{
        return Repository(MALAuth())
    }
}