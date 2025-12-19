package com.example.ambienta.ui.login

import androidx.lifecycle.ViewModel
import com.example.ambienta.data.UserSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {

    private val _logged = MutableStateFlow(false)
    val logged: StateFlow<Boolean> = _logged

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun login(user: String, password: String) {

        if (user.isBlank() || password.isBlank()) {
            _error.value = "Preencha usuário e senha"
            return
        }

        // Capitaliza o nome do usuário
        val formattedUser =
            user.trim().replaceFirstChar { it.uppercase() }

        UserSession.setUserName(formattedUser)
        _error.value = null
        _logged.value = true
    }
}
