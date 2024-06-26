package com.escola.compose.character.di

import com.escola.compose.character.data.remote.api.CharacterApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteApi {

    @Provides
    @Singleton
    fun provideCharacterApi(retrofit: Retrofit): CharacterApi = retrofit.create(
        CharacterApi::class.java
    )
}