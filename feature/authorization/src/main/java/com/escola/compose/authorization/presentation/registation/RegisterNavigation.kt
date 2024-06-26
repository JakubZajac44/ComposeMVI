package com.escola.compose.authorization.presentation.registation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.escola.compose.authorization.presentation.login.LoginEffect

internal const val REGISTRATION_ROUTE = "registration"

internal fun NavController.navigateToRegistrationScreen() {
    navigate(REGISTRATION_ROUTE)
}
internal fun NavGraphBuilder.registrationScreen(
    navigationEvent: (LoginEffect) -> Unit,
) {

    composable(REGISTRATION_ROUTE) {
        RegisterScreen(
        )
//        val viewModel: LoginViewModel = hiltViewModel()
//        LoginScreen(
//            biometricPromptUtils = viewModel.biometricPromptUtils,
//            loginState = viewModel.state.collectAsStateWithLifecycle(),
//            uiState = viewModel.screenState.collectAsStateWithLifecycle(),
//            updateScreenState = viewModel::updateScreenState,
//            shouldHideBiometricAuthDialog = viewModel.shouldHideBiometricAuthDialog,
//            loginUser = viewModel::loginUser,
//            loginError = viewModel::loginError,
//            userLogged = userLogged,
//            setHideBiometricAuthDialog = viewModel::setHideBiometricAuthorizationDialog,
//            canAuthenticate = viewModel.biometricAuthorizationStatus
//        )
    }
}