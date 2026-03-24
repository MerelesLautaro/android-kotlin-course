package com.example.bankingapp.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bankingapp.ui.components.AuthBackground
import com.example.bankingapp.ui.components.AuthButton
import com.example.bankingapp.ui.components.AuthErrorMessage
import com.example.bankingapp.ui.components.AuthRedirectText
import com.example.bankingapp.ui.components.AuthTextField

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel()
) {

    val state by viewModel::state

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        AuthBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Login",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                AuthTextField(
                    value = state.identifier,
                    onValueChange = { viewModel.onIdentifierChange(it) },
                    label = "Email"
                )

                AuthTextField(
                    value = state.password,
                    onValueChange = { viewModel.onPasswordChange(it) },
                    label = "Password",
                    isPassword = true
                )

                AuthErrorMessage(
                    message = state.error
                )

                AuthButton(
                    text = "Sign in",
                    onClick = { viewModel.onLoginClick() }
                )

                AuthRedirectText(
                    text = "¿No tenés una cuenta?",
                    actionText = "Registrate",
                    onClick = {
                        // next stage navController.navigate("register")
                    }
                )

            }

        }

    }

}