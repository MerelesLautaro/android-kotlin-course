package com.example.bankingapp.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set


    fun onIdentifierChange(value: String) {
        state = state.copy(identifier = value)
    }

    fun onPasswordChange(value: String) {
        state = state.copy(password = value)
    }

    fun onLoginClick() {

        if (state.identifier.isBlank() || state.password.isBlank()) {
            state = state.copy(error = "Todos los campos son obligatorios")
            return
        }

        // luego aquí llamaremos a la API
    }

}