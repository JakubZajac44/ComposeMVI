package com.escola.compose.character.data.remote.data_source

import com.escola.compose.character.data.remote.api.CharacterApi
import com.escola.compose.character.data.remote.model.CharacterDto
import com.escola.compose.character.data.remote.model.EpisodeDto
import com.escola.compose.character.data.remote.response.ApiResponse
import com.escola.compose.resource.ApiResult
import com.escola.compose.resource.apiCall
import kotlinx.coroutines.delay
import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    private val api: CharacterApi,
) {

    suspend fun getCharacterListByPage(page: Int): ApiResult<ApiResponse<CharacterDto>> {
        delay(3000)
        return apiCall(call = { api.getCharacterList(page) })
    }

    suspend fun getCharacterDetails(id: String): ApiResult<CharacterDto> {
        return apiCall(call = { api.getCharacterDetails(id) })
    }

    suspend fun getCharacterEpisode(episodeId: String): ApiResult<EpisodeDto> {
        return apiCall(call = { api.getCharacterEpisode(episodeId) })
    }
}