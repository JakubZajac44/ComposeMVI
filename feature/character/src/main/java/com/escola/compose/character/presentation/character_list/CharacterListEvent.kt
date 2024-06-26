package com.escola.compose.character.presentation.character_list

import com.escola.compose.resource.viewmodel.Reducer

sealed class CharacterListEvent : Reducer.ViewEvent{
}

sealed class CharacterListEffect : Reducer.ViewEffect{
    data class OnCharacterDetailsClick(val characterId: String, val characterName: String, val characterUrlImage: String ): CharacterListEffect()
}
