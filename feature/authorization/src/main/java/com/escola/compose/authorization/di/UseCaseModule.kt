package com.escola.compose.authorization.di

import com.escola.compose.authorization.domain.repository.AuthorizationRepository
import com.escola.compose.authorization.domain.use_case.LoginUserUseCase
import com.escola.compose.authorization.domain.use_case.ValidateLoginDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideLoginUserUseCase(authorizationRepository: AuthorizationRepository): LoginUserUseCase =
        LoginUserUseCase(authorizationRepository)

    @Provides
    @Singleton
    fun provideValidateLoginDataUseCase(): ValidateLoginDataUseCase =
        ValidateLoginDataUseCase()
}