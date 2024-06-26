package com.escola.compose.character.presentation.character

import com.escola.compose.resource.viewmodel.Reducer



sealed class CharacterEvent: Reducer.ViewEvent{
    data object RefreshData: CharacterEvent()
}

sealed class CharacterEffect: Reducer.ViewEffect {
    data object OnBackClick : CharacterEffect()
}
