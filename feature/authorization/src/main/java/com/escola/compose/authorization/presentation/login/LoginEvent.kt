package com.escola.compose.authorization.presentation.login

import com.escola.compose.resource.viewmodel.Reducer

sealed class LoginEvent: Reducer.ViewEvent{
    data object LoginUser : LoginEvent()
    data class LoginChanged(val login: String): LoginEvent()
    data class PasswordChanged(val password: String): LoginEvent()
}

sealed class LoginNavigation {

    data object ForgotPasswordClick: LoginNavigation()
    data object RegisterClick: LoginNavigation()
    data object RegulationClick: LoginNavigation()
    data object NavigateToHome: LoginNavigation()
}

sealed class LoginEffect: Reducer.ViewEffect{
    data class ShowToast(val message: String): LoginEffect()
    data object UserLogged: LoginEffect()
}
