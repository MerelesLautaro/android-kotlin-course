package com.example.bankingapp.ui.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankingapp.domain.repository.AuthRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class RegisterViewModel(
    private val repository: AuthRepository
) : ViewModel() {

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

        state = state.copy(error = null)

        viewModelScope.launch {

            val result = repository.register(
                name = state.name,
                email = state.email,
                password = state.password
            )

            result
                .onSuccess {
                    state = state.copy(
                        successMessage = "User created successfully"
                    )
                }
                .onFailure {

                    state = state.copy(
                        error = it.message ?: "Unknown error"
                    )

                }

        }

    }

}