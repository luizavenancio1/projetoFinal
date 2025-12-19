package com.example.ambienta.ui.profile

import androidx.lifecycle.ViewModel
import com.example.ambienta.data.UserSession
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel : ViewModel() {

    val userName: StateFlow<String> = UserSession.userName

    fun updateName(newName: String) {
        if (newName.isNotBlank()) {
            UserSession.setUserName(newName)
        }
    }
}
