package com.example.ambienta.data

import com.example.ambienta.data.remote.WeatherApi
import com.example.ambienta.data.repository.AirRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AirModule {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.open-meteo.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val repository = AirRepository(
        retrofit.create(WeatherApi::class.java)
    )
}
