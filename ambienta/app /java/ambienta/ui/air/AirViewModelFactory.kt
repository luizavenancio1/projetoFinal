package com.example.ambienta.ui.air

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ambienta.data.repository.AirRepository

class AirViewModelFactory(
    private val repository: AirRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AirViewModel::class.java)) {
            return AirViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel inválido")
    }
}
