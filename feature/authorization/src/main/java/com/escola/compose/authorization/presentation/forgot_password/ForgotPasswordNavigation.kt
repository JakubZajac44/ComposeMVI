package com.escola.compose.authorization.presentation.forgot_password

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val FORGOT_PASSWORD_ROUTE = "forgot_password"

internal fun NavController.navigateToForgotPasswordScreen() {
    navigate(FORGOT_PASSWORD_ROUTE)
}

internal fun NavGraphBuilder.forgotPasswordScreen(
) {

    composable(
        route = FORGOT_PASSWORD_ROUTE,
    ) {
        ForgotPasswordScreen()
    }
}