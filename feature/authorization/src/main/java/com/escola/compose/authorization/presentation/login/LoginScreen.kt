package com.escola.compose.authorization.presentation.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.escola.compose.authorization.R
import com.escola.compose.authorization.domain.use_case.ValidateLoginDataError
import com.escola.compose.resource.util.SingleEventEffect
import com.escola.compose.resource.ui.component.container.LoadingBox
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun LoginScreen(
    loginEvent: (LoginEvent) -> Unit,
    loginEffect: Flow<LoginEffect>,
    navigationEvent: (LoginNavigation) -> Unit,
    loginState: LoginState,
) {

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    SingleEventEffect(sideEffectFlow = loginEffect) { effect ->
        when (effect) {
            is LoginEffect.ShowToast -> Toast.makeText(
                context,
                effect.message,
                Toast.LENGTH_SHORT
            ).show()

            LoginEffect.UserLogged ->  navigationEvent.invoke(LoginNavigation.NavigateToHome)
        }
    }


    LoadingBox(
        isLoading = loginState.isLoading
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {

            Column(
                modifier = Modifier
                    .padding(horizontal = 40.dp, vertical = 20.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.app_name_login),
                    fontSize = 30.sp,
                )
                Spacer(modifier = Modifier.height(20.dp))
                TextField(modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = stringResource(R.string.login)) },
                    value = loginState.login,
                    isError = loginState.loginDataNotValid is ValidateLoginDataError.LoginError,
                    onValueChange = {
                        loginEvent.invoke(LoginEvent.LoginChanged(it))
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),
                    supportingText = {
                        if (loginState.loginDataNotValid is ValidateLoginDataError.LoginError) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = loginState.loginDataNotValid.messageError,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    },
                    trailingIcon = {
                        if (loginState.loginDataNotValid is ValidateLoginDataError.LoginError) Icon(
                            Icons.Filled.Clear, "error", tint = MaterialTheme.colorScheme.error
                        )
                    })


                Spacer(modifier = Modifier.height(10.dp))

                TextField(modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = stringResource(R.string.password)) },
                    value = loginState.password,
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true,
                    isError = loginState.loginDataNotValid is ValidateLoginDataError.PasswordError,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.clearFocus()
                        loginEvent.invoke(LoginEvent.LoginUser)
                    }),
                    onValueChange = {
                        loginEvent.invoke(LoginEvent.PasswordChanged(it))
                    },
                    supportingText = {
                        if (loginState.loginDataNotValid is ValidateLoginDataError.PasswordError) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = loginState.loginDataNotValid.messageError,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    },
                    trailingIcon = {
                        if (loginState.loginDataNotValid is ValidateLoginDataError.PasswordError) Icon(
                            Icons.Filled.Clear, "error", tint = MaterialTheme.colorScheme.error
                        )
                    })

                Spacer(modifier = Modifier.height(5.dp))

                Button(modifier = Modifier.fillMaxWidth(), onClick = {
                    loginEvent.invoke(LoginEvent.LoginUser)
                }) {
                    Text(text = stringResource(R.string.login_action))
                }

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    style = TextStyle(textAlign = TextAlign.Center),
                    text = stringResource(R.string.no_account_yet)
                )


                Spacer(modifier = Modifier.height(15.dp))

                Button(modifier = Modifier.fillMaxWidth(), onClick = {
                    navigationEvent.invoke(LoginNavigation.RegisterClick)
                }) {
                    Text(text = stringResource(R.string.register_new_account))
                }

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    modifier = Modifier.clickable {
                        navigationEvent.invoke(LoginNavigation.RegulationClick)
                    },
                    style = TextStyle(textAlign = TextAlign.Center),
                    text = stringResource(R.string.regulation)
                )


            }
            Text(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(20.dp)
                    .clickable {
                        navigationEvent.invoke(LoginNavigation.ForgotPasswordClick)
                    },
                text = stringResource(R.string.dont_forget_password),
                style = TextStyle(textAlign = TextAlign.Center)
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(loginEvent = {},
        navigationEvent = {},
        loginEffect = flow {},
        loginState = LoginState(isLoading = false)
    )
}

@Preview
@Composable
fun LoginScreenLoadingPreview() {
    LoginScreen(loginEvent = {},
        navigationEvent = {},
        loginEffect = flow {},
        loginState = LoginState(isLoading = true)
    )
}