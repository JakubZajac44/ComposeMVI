package com.escola.compose.character.presentation.character

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import java.net.URLDecoder

const val CHARACTER_ID = "characterId"
const val CHARACTER_NAME = "characterName"
const val CHARACTER_IMAGE_URL = "characterImageUrl"
internal const val CHARACTER_ROUTE =
    "character_details/{$CHARACTER_ID}/{$CHARACTER_NAME}/{$CHARACTER_IMAGE_URL}"

internal fun NavController.navigateToCharacterScreen(
    characterId: String,
    characterName: String,
    characterImageUrl: String,
) {
    navigate(
        CHARACTER_ROUTE.replace("{$CHARACTER_ID}", characterId)
            .replace("{$CHARACTER_NAME}", characterName)
            .replace("{$CHARACTER_IMAGE_URL}", characterImageUrl)
    )
}


@OptIn(ExperimentalSharedTransitionApi::class)
internal fun NavGraphBuilder.characterScreen(
    sharedTransitionScope: SharedTransitionScope,
    navigationEvent: (CharacterNavigationEvent) -> Unit,
) {
    composable(
        route = CHARACTER_ROUTE,
        arguments = listOf(navArgument(CHARACTER_ID) {
            type = NavType.StringType
        }, navArgument(CHARACTER_NAME) {
            type = NavType.StringType
        }, navArgument(CHARACTER_IMAGE_URL) {
            type = NavType.StringType
        })
    ) { backStackEntry ->
        val characterId = backStackEntry.arguments?.getString(CHARACTER_ID) ?: ""
        val name = backStackEntry.arguments?.getString(CHARACTER_NAME) ?: ""
        val urlDecoded = backStackEntry.arguments?.getString(CHARACTER_IMAGE_URL) ?: ""
        val viewModel: CharacterViewModel = hiltViewModel()
        viewModel.setInitialData(characterId, name, urlDecoded)

        sharedTransitionScope.CharacterScreen(
            animatedVisibilityScope = this@composable,
            state = viewModel.state.collectAsStateWithLifecycle().value,
            navigationEvent = navigationEvent,
            characterEvent = viewModel::onEvent
        )
    }
}