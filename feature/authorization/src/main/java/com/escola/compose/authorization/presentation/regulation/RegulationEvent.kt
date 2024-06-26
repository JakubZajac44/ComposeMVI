package com.escola.compose.authorization.presentation.regulation

sealed class RegulationNavigationEvent {

    data object OnBackClick: RegulationNavigationEvent()
}
