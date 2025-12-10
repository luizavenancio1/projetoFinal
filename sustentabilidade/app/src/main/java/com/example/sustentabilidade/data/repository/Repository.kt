package com.example.sustentabilidade.data.repository

import com.example.sustentabilidade.data.api.RetrofitClient

class Repository {
    suspend fun getTips(): Result<List<com.example.sustentabilidade.data.model.Tips>> {
        return try {
            val response = RetrofitClient.api.getTips()
            Result.success(response.data)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
