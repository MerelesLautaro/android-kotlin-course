package com.example.bankingapp.data.remote.util

import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.Response

object ErrorParser {

    private val gson = Gson()

    fun parseError(response: Response<*>): String {

        return try {

            val errorBody = response.errorBody()?.string()

            if (errorBody.isNullOrEmpty()) {
                return "Unknown error"
            }

            val json = gson.fromJson(errorBody, JsonObject::class.java)

            when {

                json.has("error") ->
                    json.get("error").asString

                else ->
                    json.entrySet().firstOrNull()?.value?.asString
                        ?: "Unknown error"
            }

        } catch (e: Exception) {

            "Unknown error"

        }

    }

}