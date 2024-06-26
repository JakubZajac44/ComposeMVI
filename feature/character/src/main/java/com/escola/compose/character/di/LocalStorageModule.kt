package com.escola.compose.character.di

import android.content.Context
import com.escola.compose.character.data.local.data_source.CharacterLocalDataSource
import com.escola.compose.character.data.local.dao.CharacterDao
import com.escola.compose.character.data.local.dao.CharacterKeyDao
import com.escola.compose.character.data.local.db.CharacterDateBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalStorageModule {

    @Provides
    @Singleton
    fun provideBookDatabase(@ApplicationContext context: Context): CharacterDateBase =
        CharacterDateBase.create(context)

    @Provides
    @Singleton
    fun provideCharacterDao(database: CharacterDateBase): CharacterDao = database.characterDao()

    @Provides
    @Singleton
    fun provideCharacterKeyDao(database: CharacterDateBase): CharacterKeyDao =
        database.characterRemoteKeyDao()


}