package com.escola.compose.character.presentation.character_list

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems

internal const val CHARACTER_LIST_ROUTE = "character_list_rout"

@OptIn(ExperimentalSharedTransitionApi::class)
internal fun NavGraphBuilder.characterLisScreen(
    navigationEvent: (CharacterListEffect) -> Unit,
    sharedTransitionScope: SharedTransitionScope
) {
    composable(CHARACTER_LIST_ROUTE) {
        val characterListViewModel = hiltViewModel<CharacterListViewModel>()
        val characterList = characterListViewModel.characterListFlow.collectAsLazyPagingItems()

        sharedTransitionScope.CharacterListScreen(
            state = characterListViewModel.state.collectAsStateWithLifecycle().value,
            list = characterList,
            characterListEvent = characterListViewModel::onEvent,
            navigationEvent = navigationEvent,
            animatedVisibilityScope =  this@composable,
        )
    }
}