package com.escola.compose.character.presentation.character

sealed class CharacterEvent{
    data object RefreshData: CharacterEvent()
}

sealed class CharacterNavigationEvent {
    data object OnBackClick : CharacterNavigationEvent()
}
