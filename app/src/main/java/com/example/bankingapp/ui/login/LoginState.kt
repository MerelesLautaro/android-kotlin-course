package com.example.bankingapp.ui.login

data class LoginState(

    val identifier: String = "",
    val password: String = "",

    val isLoading: Boolean = false,

    val error: String? = null

)