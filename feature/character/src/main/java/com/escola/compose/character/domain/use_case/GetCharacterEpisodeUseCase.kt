package com.escola.compose.character.domain.use_case

import android.util.Log
import com.escola.compose.character.data.mapper.toEpisodeModel
import com.escola.compose.character.domain.model.EpisodeModel
import com.escola.compose.character.domain.repository.CharacterRepository
import com.escola.compose.resource.ApiResult
import com.escola.compose.resource.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCharacterEpisodeUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
) {

    suspend operator fun invoke(episodeId: String): Flow<Resource<EpisodeModel>> {

        return flow {
            emit(Resource.Loading())
            delay(2000)
            when (val apiResponse = characterRepository.getCharacterEpisode(episodeId)) {
                is ApiResult.Error -> {
                    Log.e("Error", "Error respone ${apiResponse.exception.message}")
                    emit(Resource.Error(apiResponse.exception, apiResponse.exception.message))
                }

                is ApiResult.Success -> {
                    emit(Resource.Success(apiResponse.data.toEpisodeModel()))
                }

            }
        }
    }
}