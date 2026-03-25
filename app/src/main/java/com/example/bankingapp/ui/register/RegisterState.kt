package com.example.bankingapp.ui.register

data class RegisterState (

    val name: String = "",
    val password: String = "",
    val email: String = "",

    val isLoading: Boolean = false,

    val error: String? = null,

    val successMessage: String? = null,

    val isRegistered: Boolean = false

)