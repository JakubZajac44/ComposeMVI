package com.escola.compose.authorization.di

import com.escola.compose.authorization.data.remote.AuthorizationApi
import com.escola.compose.authorization.data.remote.AuthorizationRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun provideAuthorizationRemoteDataSource(api: AuthorizationApi): AuthorizationRemoteDataSource =
        AuthorizationRemoteDataSource(api)
}