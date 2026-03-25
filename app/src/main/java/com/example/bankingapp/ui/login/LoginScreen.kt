package com.example.bankingapp.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bankingapp.data.local.TokenManager
import com.example.bankingapp.data.remote.RetrofitClient
import com.example.bankingapp.data.remote.api.AuthApi
import com.example.bankingapp.data.repository.AuthRepositoryImpl
import com.example.bankingapp.ui.components.AuthBackground
import com.example.bankingapp.ui.components.AuthButton
import com.example.bankingapp.ui.components.AuthErrorMessage
import com.example.bankingapp.ui.components.AuthRedirectText
import com.example.bankingapp.ui.components.AuthTextField

@Composable
fun LoginScreen(
    navController: NavController
) {

    val context = LocalContext.current

    val viewModel: LoginViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>): T {

                val tokenManager = TokenManager(context)

                val retrofit = RetrofitClient.create(context)
                val authApi = retrofit.create(AuthApi::class.java)

                val repository = AuthRepositoryImpl(authApi)

                return LoginViewModel(tokenManager, repository) as T
            }

        }
    )

    val state = viewModel.state

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

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
                        navController.navigate("register") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                )

            }

        }

    }

}