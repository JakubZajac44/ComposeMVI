package com.escola.compose.character.domain.use_case

import androidx.paging.PagingData
import com.escola.compose.character.domain.model.CharacterModel
import com.escola.compose.character.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCharacterListUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
) {
    operator fun invoke(): Flow<PagingData<CharacterModel>> {
        return characterRepository.getCharacterListByPage()
    }
}