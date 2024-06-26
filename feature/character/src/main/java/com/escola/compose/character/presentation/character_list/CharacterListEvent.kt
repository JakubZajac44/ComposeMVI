package com.escola.compose.character.presentation.character_list

sealed class CharacterListEvent{
}

sealed class CharacterListNavigationEvent {
    data class OnCharacterDetailsClick(val characterId: String, val characterName: String, val characterUrlImage: String ): CharacterListNavigationEvent()
}
