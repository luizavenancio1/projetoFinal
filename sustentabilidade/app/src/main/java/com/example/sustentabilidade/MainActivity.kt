package com.example.sustentabilidade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sustentabilidade.ui.screens.TipsScreen
import com.example.sustentabilidade.ui.screens.DetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val nav = rememberNavController()
            NavHost(navController = nav, startDestination = "home") {
                composable("home") { TipsScreen(nav) }
                composable("detail/{id}") { backStack ->
                    DetailScreen(backStack.arguments?.getString("id") ?: "")
                }
            }
        }
    }
}
