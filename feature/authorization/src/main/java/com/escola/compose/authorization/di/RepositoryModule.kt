package com.escola.compose.authorization.di

import com.escola.compose.authorization.data.remote.AuthorizationRemoteDataSource
import com.escola.compose.authorization.data.repository.AuthorizationRepositoryImpl
import com.escola.compose.authorization.domain.repository.AuthorizationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthorizationRepository(
        authorizationRemoteDataSource: AuthorizationRemoteDataSource
    ): AuthorizationRepository = AuthorizationRepositoryImpl(
        authorizationRemoteDataSource
    )
}