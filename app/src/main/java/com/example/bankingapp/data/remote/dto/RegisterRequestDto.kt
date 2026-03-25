package com.example.bankingapp.data.remote.dto

data class RegisterRequestDto(
    val name: String,
    val email: String,
    val password: String
)