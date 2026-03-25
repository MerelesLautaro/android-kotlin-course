package com.example.bankingapp.data.remote.dto
data class UserDetailsResponseDto(
    val name: String,
    val email: String,
    val accountNumber: String,
    val accountType: String,
    val hashedPassword: String
)