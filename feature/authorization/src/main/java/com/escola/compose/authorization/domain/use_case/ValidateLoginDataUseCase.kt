package com.escola.compose.authorization.domain.use_case

import javax.inject.Inject

class ValidateLoginDataUseCase @Inject constructor() {
    operator fun invoke(login: String, password: String): ValidateLoginDataError {
        return if (login.isEmpty()) ValidateLoginDataError.LoginError("Pole Login musi być uzupełnione")
        else if (password.isEmpty()) ValidateLoginDataError.PasswordError("Pole Hasło musi być uzupełnione")
        else ValidateLoginDataError.NoError
    }
}

sealed class ValidateLoginDataError{
    data class LoginError(val messageError: String): ValidateLoginDataError()
    data class PasswordError(val messageError: String): ValidateLoginDataError()
    data object NoError: ValidateLoginDataError()
}

