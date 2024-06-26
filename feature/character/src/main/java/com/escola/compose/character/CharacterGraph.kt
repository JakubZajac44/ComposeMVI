package com.escola.compose.character

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.escola.compose.character.presentation.character.CHARACTER_ID
import com.escola.compose.character.presentation.character.CharacterNavigationEvent
import com.escola.compose.character.presentation.character.characterScreen
import com.escola.compose.character.presentation.character.navigateToCharacterScreen
import com.escola.compose.character.presentation.character_list.CHARACTER_LIST_ROUTE
import com.escola.compose.character.presentation.character_list.CharacterListNavigationEvent
import com.escola.compose.character.presentation.character_list.characterLisScreen
import java.net.URLEncoder


fun NavController.navigateToCharacterGraph() {
    navigate(CHARACTER_GRAPH_ROUTE) {
        popUpTo(graph.id) {
            inclusive = true
        }
    }
}

internal data class CharacterIdArgs(val characterId: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        characterId = checkNotNull(savedStateHandle[CHARACTER_ID])
    )
}

const val CHARACTER_GRAPH_ROUTE = "character-graph"

@OptIn(ExperimentalSharedTransitionApi::class)

fun NavGraphBuilder.characterGraph(
    navController: NavController,
    sharedTransitionScope: SharedTransitionScope,
) {


    navigation(
        route = CHARACTER_GRAPH_ROUTE,
        startDestination = CHARACTER_LIST_ROUTE
    ) {

        characterLisScreen(
            navigationEvent = { navigationEvent ->
                when (navigationEvent) {
                    is CharacterListNavigationEvent.OnCharacterDetailsClick -> {
                        val encoded = URLEncoder.encode(navigationEvent.characterUrlImage, "UTF-8")
                        navController.navigateToCharacterScreen(
                            characterName = navigationEvent.characterName,
                            characterId = navigationEvent.characterId,
                            characterImageUrl = encoded
                        )
                    }
                }
            },

            sharedTransitionScope = sharedTransitionScope
        )

        characterScreen(
            sharedTransitionScope = sharedTransitionScope,
            navigationEvent = { navigationEvent ->
                when (navigationEvent) {
                    is CharacterNavigationEvent.OnBackClick -> {
                        navController.navigateUp()
                    }
                }
            },
        )
    }


}