package com.example.ambienta.data.repository

import com.example.ambienta.data.remote.OpenMeteoResponse
import com.example.ambienta.data.remote.WeatherApi

class AirRepository(
    private val api: WeatherApi
) {

    suspend fun fetchWeather(): OpenMeteoResponse {
        return api.getWeather(
            latitude = -8.6847,   // Palmares - PE
            longitude = -35.5914
        )
    }
}
