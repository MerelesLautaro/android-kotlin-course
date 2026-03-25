package com.example.bankingapp.domain.repository

import com.example.bankingapp.data.remote.dto.AccountDetailResponseDto
import com.example.bankingapp.data.remote.dto.UserDetailsResponseDto

interface DashboardRepository {

    suspend fun getUserInfo(): Result<UserDetailsResponseDto>

    suspend fun getAccountDetail(): Result<AccountDetailResponseDto>

    suspend fun getAccountByIndex(index: String): Result<AccountDetailResponseDto>
}