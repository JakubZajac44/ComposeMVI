package com.escola.compose.authorization.presentation.login

sealed class LoginEvent{
    data object LoginUser : LoginEvent()
    data class LoginChanged(val login: String): LoginEvent()
    data class PasswordChanged(val password: String): LoginEvent()
}

sealed class LoginNavigationEvent {

    data object ForgotPasswordClick: LoginNavigationEvent()
    data object RegisterClick: LoginNavigationEvent()
    data object RegulationClick: LoginNavigationEvent()
    data object NavigateToHome: LoginNavigationEvent()
}
