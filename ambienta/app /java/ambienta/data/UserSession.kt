package com.example.ambienta.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object UserSession {

    private val _userName = MutableStateFlow("Usuário")
    val userName: StateFlow<String> = _userName

    fun setUserName(name: String) {
        _userName.value = name
    }

    fun clear() {
        _userName.value = ""
    }
}
