package com.escola.compose.authorization.presentation.login

import androidx.lifecycle.viewModelScope
import com.escola.compose.authorization.domain.use_case.LoginUserUseCase
import com.escola.compose.authorization.domain.use_case.ValidateLoginDataError
import com.escola.compose.authorization.domain.use_case.ValidateLoginDataUseCase
import com.escola.compose.resource.Resource
import com.escola.compose.resource.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase,
    private val validateLoginDataUseCase: ValidateLoginDataUseCase
) : BaseViewModel<LoginState, LoginEvent, LoginEffect>(
    initialState = LoginState()
) {

    override fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.LoginUser -> loginUser(_state.value.login, _state.value.password)
            is LoginEvent.LoginChanged -> _state.update { loginState ->
                loginState.copy(
                    login = event.login
                )
            }
            is LoginEvent.PasswordChanged -> _state.update { loginState ->
                loginState.copy(
                    password = event.password
                )
            }
        }

    }

    private fun loginUser(login: String, password: String) {
        val loginDataInvalidMessage = validateLoginDataUseCase.invoke(login, password)
        _state.update { loginState ->
            loginState.copy(
                loginDataNotValid = loginDataInvalidMessage
            )
        }

        if (loginDataInvalidMessage !is ValidateLoginDataError.NoError) return

        viewModelScope.launch {
            loginUserUseCase.invoke(login, password)
                .collect { result ->
                    when (result) {
                        is Resource.Error -> {
                            _state.update { loginState ->
                                loginState.copy(
                                    loginError = result.message,
                                    isLoading = false,
                                    isLogged = false
                                )
                            }

                        }

                        is Resource.Loading -> {
                            _state.update { loginState ->
                                loginState.copy(
                                    isLoading = result.isLoading
                                )
                            }
                        }

                        is Resource.Success -> {
                            sendEffect(LoginEffect.UserLogged)
                        }
                    }
                }
        }
    }
}