package com.escola.compose.character.presentation.character_list

import com.escola.compose.resource.viewmodel.Reducer


data class CharacterListState(
    val isRefreshing: Boolean = false
): Reducer.ViewState