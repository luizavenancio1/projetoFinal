package com.example.ambienta.ui.air

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ambienta.data.repository.AirRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AirViewModel(
    private val repository: AirRepository
) : ViewModel() {

    private val _state = MutableStateFlow("Carregando clima de Palmares...")
    val state: StateFlow<String> = _state

    fun load() {
        viewModelScope.launch {
            try {
                val response = repository.fetchWeather()

                _state.value = """
                    📍 Recife - PE
                    🌡️ Temperatura: ${response.current_weather.temperature}°C
                    💨 Vento: ${response.current_weather.windspeed} km/h
                """.trimIndent()

            } catch (e: Exception) {
                _state.value = "Erro ao carregar dados climáticos 🌥️"
            }
        }
    }
}
