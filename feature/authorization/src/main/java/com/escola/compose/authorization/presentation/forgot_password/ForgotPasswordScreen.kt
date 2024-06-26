package com.escola.compose.authorization.presentation.forgot_password

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.escola.compose.authorization.presentation.login.LoginEvent

@Composable
fun ForgotPasswordScreen() {

    Box(modifier = Modifier
        .fillMaxSize()){

        Button(onClick = {
        }) {
            Text(text = "Przypomnij")
        }

    }
}