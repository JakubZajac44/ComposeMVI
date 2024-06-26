package com.escola.compose.authorization

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.escola.compose.authorization.presentation.forgot_password.forgotPasswordScreen
import com.escola.compose.authorization.presentation.forgot_password.navigateToForgotPasswordScreen
import com.escola.compose.authorization.presentation.login.LOGIN_ROUTE
import com.escola.compose.authorization.presentation.login.LoginNavigationEvent
import com.escola.compose.authorization.presentation.login.loginScreen
import com.escola.compose.authorization.presentation.registation.navigateToRegistrationScreen
import com.escola.compose.authorization.presentation.registation.registrationScreen
import com.escola.compose.authorization.presentation.regulation.RegulationNavigationEvent
import com.escola.compose.authorization.presentation.regulation.navigateToRegulationScreen
import com.escola.compose.authorization.presentation.regulation.regulationScreen

const val AUTHORIZATION_GRAPH_ROUTE = "authorization-graph"
fun NavGraphBuilder.authorizationGraph(
    userLogged: () -> Unit,
    navController: NavController,
) {
    navigation(
        route = AUTHORIZATION_GRAPH_ROUTE,
        startDestination = LOGIN_ROUTE
    ) {
        loginScreen(
            navigationEvent = { navigationEvent ->
                when (navigationEvent) {
                    LoginNavigationEvent.ForgotPasswordClick -> navController.navigateToForgotPasswordScreen()
                    LoginNavigationEvent.RegisterClick -> navController.navigateToRegistrationScreen()
                    LoginNavigationEvent.RegulationClick -> navController.navigateToRegulationScreen()
                    LoginNavigationEvent.NavigateToHome -> userLogged.invoke()
                }
            }
        )

        registrationScreen(
            navigationEvent = { event ->

            },
        )

        regulationScreen(
            navigationEvent = { event ->
                Log.e("EVENT", "Event: $event")
                when (event) {
                    RegulationNavigationEvent.OnBackClick -> navController.navigateUp()
                }
            },
        )

        forgotPasswordScreen()
    }
}