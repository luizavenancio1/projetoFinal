package com.example.sustentabilidade.data.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {
    val api: Api = Retrofit.Builder()
        .baseUrl("https://sustentabilidade-api-8lua.onrender.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(Api::class.java)
}