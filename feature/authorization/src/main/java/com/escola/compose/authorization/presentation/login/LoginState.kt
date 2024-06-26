package com.escola.compose.authorization.presentation.login

import com.escola.compose.authorization.domain.use_case.ValidateLoginDataError

data class LoginState(
    val login: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isLogged: Boolean = false,
    val loginError: String? = null,
    val loginDataNotValid: ValidateLoginDataError = ValidateLoginDataError.NoError
)


