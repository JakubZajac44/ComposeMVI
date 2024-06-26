package com.escola.compose.character.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.escola.compose.character.data.local.data_source.CharacterLocalDataSource
import com.escola.compose.character.data.local.db.CharacterDateBase
import com.escola.compose.character.data.local.model.CharacterEntity
import com.escola.compose.character.data.remote.data_source.CharacterRemoteDataSource
import com.escola.compose.character.data.remote.mediator.CharacterRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {


    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideBookPager(
        characterDatabase: CharacterDateBase,
        characterRemotePagingDataSource: CharacterRemoteDataSource,
        characterLocalDataSource: CharacterLocalDataSource,
    ): Pager<Int, CharacterEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = CharacterRemoteMediator(
                characterDb = characterDatabase,
                characterRemoteDataSource = characterRemotePagingDataSource,
                characterLocalDataSource = characterLocalDataSource
            ),
            pagingSourceFactory = characterLocalDataSource::getCharacterListPaged
        )
    }
}