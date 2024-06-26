package com.escola.compose.character.di

import com.escola.compose.character.data.local.dao.CharacterDao
import com.escola.compose.character.data.local.dao.CharacterKeyDao
import com.escola.compose.character.data.local.data_source.CharacterLocalDataSource
import com.escola.compose.character.data.remote.api.CharacterApi
import com.escola.compose.character.data.remote.data_source.CharacterRemoteDataSource
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
    fun provideCharacterRemoteDataSource(api: CharacterApi): CharacterRemoteDataSource =
        CharacterRemoteDataSource(api)

    @Provides
    @Singleton
    fun provideCharacterLocalDataSource(
        characterDao: CharacterDao,
        characterKeyDao: CharacterKeyDao,
    ): CharacterLocalDataSource = CharacterLocalDataSource(
        characterDao = characterDao,
        characterKeyDao = characterKeyDao
    )
}

