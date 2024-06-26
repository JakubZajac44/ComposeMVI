package com.escola.compose.authorization.presentation.regulation

import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog


internal const val REGULATION_ROUTE = "regulation"

internal fun NavController.navigateToRegulationScreen() {
    navigate(REGULATION_ROUTE)
}

internal fun NavGraphBuilder.regulationScreen(
    navigationEvent: (RegulationNavigationEvent) -> Unit,
) {

    dialog(
        route = REGULATION_ROUTE,
        dialogProperties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        RegulationScreen(
            navigationEvent = navigationEvent
        )
    }
}