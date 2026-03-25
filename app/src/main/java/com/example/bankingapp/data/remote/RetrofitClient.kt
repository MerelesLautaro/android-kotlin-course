package com.example.bankingapp.data.remote

import android.content.Context
import com.example.bankingapp.data.remote.api.AuthApi
import com.example.bankingapp.data.remote.util.ApiConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.bankingapp.data.remote.interceptor.AuthInterceptor
import com.example.bankingapp.data.local.TokenManager

object RetrofitClient {

    fun create(context: Context): AuthApi {

        val tokenManager = TokenManager(context)

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(tokenManager))
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(ApiConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(AuthApi::class.java)

    }

}