package com.escola.compose.authorization.di

import com.escola.compose.authorization.data.remote.AuthorizationApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Provides
    @Singleton
    fun provideHomeApi(retrofit: Retrofit): AuthorizationApi = retrofit.create(
        AuthorizationApi::class.java
    )
}