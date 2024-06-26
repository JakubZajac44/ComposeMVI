package com.escola.compose.authorization.presentation.login

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val LOGIN_ROUTE = "login"

internal fun NavGraphBuilder.loginScreen(
    navigationEvent: (LoginNavigationEvent) -> Unit
) {
    composable(LOGIN_ROUTE) {
        val loginViewModel = hiltViewModel<LoginViewModel>()
        LoginScreen(
            loginEvent = loginViewModel::onEvent,
            navigationEvent = navigationEvent,
            loginState = loginViewModel.state.collectAsStateWithLifecycle().value
        )
    }
}