package com.example.sustentabilidade.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sustentabilidade.data.model.Tips
import com.example.sustentabilidade.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class UiState {
    object Loading:UiState()
    data class Success(val data:List<Tips>):UiState()
    data class Error(val msg:String):UiState()
}

class TipsViewModel:ViewModel() {
    private val repo = Repository()
    private val _state = MutableStateFlow<UiState>(UiState.Loading)
    val state:StateFlow<UiState> = _state

    init { load() }

    fun load() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            val r = repo.getTips()
            if (r.isSuccess) _state.value = UiState.Success(r.getOrNull()!!)
            else _state.value = UiState.Error("Erro ao carregar")
        }
    }
}
