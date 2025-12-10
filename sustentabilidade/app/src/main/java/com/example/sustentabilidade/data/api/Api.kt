package com.example.sustentabilidade.data.api

import com.example.sustentabilidade.data.model.TipsResponse
import retrofit2.http.GET

interface Api {
    @GET("tips")
    suspend fun getTips(): TipsResponse
}
