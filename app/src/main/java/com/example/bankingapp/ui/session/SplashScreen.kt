package com.example.bankingapp.ui.session

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bankingapp.data.local.SessionManager
import com.example.bankingapp.data.local.TokenManager

@Composable
fun SplashScreen(
    navController: NavController
) {

    val context = LocalContext.current

    val viewModel: SessionViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>): T {

                val tokenManager = TokenManager(context)
                val sessionManager = SessionManager(tokenManager)

                return SessionViewModel(sessionManager) as T
            }

        }
    )

    val isLoggedIn = viewModel.isLoggedIn

    LaunchedEffect(isLoggedIn) {

        when (isLoggedIn) {

            true -> {
                navController.navigate("home") {
                    popUpTo("splash") { inclusive = true }
                }
            }

            false -> {
                navController.navigate("login") {
                    popUpTo("splash") { inclusive = true }
                }
            }

            null -> {}

        }

    }

}