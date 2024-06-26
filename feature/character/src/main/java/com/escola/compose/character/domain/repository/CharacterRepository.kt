package com.escola.compose.character.domain.repository

import androidx.paging.PagingData
import com.escola.compose.character.data.remote.model.CharacterDto
import com.escola.compose.character.data.remote.model.EpisodeDto
import com.escola.compose.character.domain.model.CharacterModel
import com.escola.compose.resource.ApiResult
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharacterListByPage(): Flow<PagingData<CharacterModel>>

    suspend fun getCharacterDetails(id: String): ApiResult<CharacterDto>

    suspend fun getCharacterEpisode(episodeId: String): ApiResult<EpisodeDto>
}