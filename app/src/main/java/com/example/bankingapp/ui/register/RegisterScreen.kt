package com.example.bankingapp.ui.register

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bankingapp.data.remote.RetrofitClient
import com.example.bankingapp.data.remote.api.AuthApi
import com.example.bankingapp.data.repository.AuthRepositoryImpl
import com.example.bankingapp.ui.components.AuthBackground
import com.example.bankingapp.ui.components.AuthButton
import com.example.bankingapp.ui.components.AuthErrorMessage
import com.example.bankingapp.ui.components.AuthRedirectText
import com.example.bankingapp.ui.components.AuthTextField
import com.example.bankingapp.ui.components.AuthSuccessDialog

@Composable
fun RegisterScreen(
    navController: NavController
) {

    val context = LocalContext.current


    val viewModel: RegisterViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>): T {

                val retrofit = RetrofitClient.create(context)
                val authApi = retrofit.create(AuthApi::class.java)

                val repository = AuthRepositoryImpl(authApi)

                return RegisterViewModel(repository) as T
            }

        }
    )

    val state = viewModel.state

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.successMessage) {
        state.successMessage?.let {
            snackbarHostState.showSnackbar(it)
        }
    }

    LaunchedEffect(state.isRegistered) {
        if (state.isRegistered) {
            navController.navigate("login") {
                popUpTo("register") { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (state.successMessage != null) {

            AuthSuccessDialog(
                message = state.successMessage!!,
                onDismiss = {
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true }
                    }
                }
            )
        }

            SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )

        AuthBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Register",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                AuthTextField(
                    value = state.name,
                    onValueChange = { viewModel.onNameChange(it) },
                    label = "Name"
                )

                AuthTextField(
                    value = state.email,
                    onValueChange = { viewModel.onEmailChange(it) },
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
                    text = "Sign up",
                    onClick = { viewModel.onRegisterClick() }
                )

                AuthRedirectText(
                    text = "¿Ya tenés una cuenta?",
                    actionText = "Iniciar sesión",
                    onClick = {
                        navController.navigate("login") {
                            popUpTo("register") { inclusive = true }
                        }
                    }
                )

            }

        }

    }

}