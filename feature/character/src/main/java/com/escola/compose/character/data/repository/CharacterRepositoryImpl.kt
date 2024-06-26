package com.escola.compose.character.data.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.escola.compose.character.data.local.model.CharacterEntity
import com.escola.compose.character.data.mapper.toCharacter
import com.escola.compose.character.data.remote.data_source.CharacterRemoteDataSource
import com.escola.compose.character.data.remote.model.CharacterDto
import com.escola.compose.character.data.remote.model.EpisodeDto
import com.escola.compose.character.domain.model.CharacterModel
import com.escola.compose.character.domain.repository.CharacterRepository
import com.escola.compose.resource.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterListRemoteMediator: Pager<Int, CharacterEntity>,
    private val characterRemoteDataSource: CharacterRemoteDataSource
) : CharacterRepository {

    override  fun getCharacterListByPage(): Flow<PagingData<CharacterModel>> {
        return characterListRemoteMediator
            .flow.map { pagingData ->
                pagingData.map {
                    it.toCharacter()
                }
            }
    }

    override suspend fun getCharacterDetails(id: String): ApiResult<CharacterDto> {
        return characterRemoteDataSource.getCharacterDetails(id)
    }

    override suspend fun getCharacterEpisode(episodeId: String): ApiResult<EpisodeDto> {
        return characterRemoteDataSource.getCharacterEpisode(episodeId)
    }
}