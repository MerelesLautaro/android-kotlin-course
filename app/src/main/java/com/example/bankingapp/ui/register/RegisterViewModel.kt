package com.example.bankingapp.ui.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {

    var state by mutableStateOf(RegisterState())
        private set


    fun onNameChange(value: String) {
        state = state.copy(name = value)
    }

    fun onEmailChange(value: String) {
        state = state.copy(email = value)
    }

    fun onPasswordChange(value: String) {
        state = state.copy(password = value)
    }

    fun onRegisterClick() {

        if (state.email.isBlank() || state.password.isBlank() || state.name.isBlank()) {
            state = state.copy(error = "Todos los campos son obligatorios")
            return
        }

        // Call API
    }

}