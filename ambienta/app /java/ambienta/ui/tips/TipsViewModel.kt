package com.example.ambienta.ui.tips

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted

class TipsViewModel : ViewModel() {

    private val _tips = MutableStateFlow(
        listOf(
            Tip(1, "Economizar água no banho 💧"),
            Tip(2, "Separar lixo reciclável ♻️"),
            Tip(3, "Evitar plástico descartável 🛍️"),
            Tip(4, "Apagar luzes ao sair do ambiente 💡"),
            Tip(5, "Usar transporte sustentável 🚲"),
            Tip(6, "Reutilizar embalagens 📦"),
            Tip(7, "Consumir alimentos locais 🥦"),
            Tip(8, "Evitar desperdício de comida 🍽️"),
            Tip(9, "Plantar árvores ou plantas 🌱"),
            Tip(10, "Reduzir consumo de energia ⚡"),
            Tip(11, "Usar garrafa reutilizável 🚰"),
            Tip(12, "Evitar impressões desnecessárias 📄"),
            Tip(13, "Dar preferência a produtos recicláveis ♻️"),
            Tip(14, "Economizar energia elétrica ⚡"),
            Tip(15, "Desligar aparelhos da tomada 🔌")
        )
    )

    val tips: StateFlow<List<Tip>> = _tips

    // 📊 Progresso calculado corretamente
    val progress: StateFlow<Int> =
        _tips
            .map { list ->
                if (list.isEmpty()) 0
                else (list.count { it.completed } * 100) / list.size
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = 0
            )

    // ✅ Marca / desmarca dica
    fun toggle(id: Int) {
        _tips.value = _tips.value.map {
            if (it.id == id) it.copy(completed = !it.completed)
            else it
        }
    }

    // 🔄 REINICIAR DICAS DO DIA
    fun resetDailyTips() {
        _tips.value = _tips.value.map {
            it.copy(completed = false)
        }
    }
}
