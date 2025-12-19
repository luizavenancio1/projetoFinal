package com.example.ambienta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.ambienta.ui.navigation.NavGraph
import com.example.ambienta.ui.theme.AmbientaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AmbientaTheme {
                NavGraph()
            }
        }
    }
}
