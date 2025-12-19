package com.example.ambienta.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.ambienta.data.AirModule
import com.example.ambienta.ui.air.*
import com.example.ambienta.ui.home.HomeScreen
import com.example.ambienta.ui.login.LoginScreen
import com.example.ambienta.ui.login.LoginViewModel
import com.example.ambienta.ui.profile.ProfileScreen
import com.example.ambienta.ui.tips.TipsScreen
import com.example.ambienta.ui.tips.TipsViewModel

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    val tipsViewModel: TipsViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {
            val vm: LoginViewModel = viewModel()
            LoginScreen(vm) {
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            }
        }

        composable("home") {
            HomeScreen(
                onTips = { navController.navigate("tips") },
                onAir = { navController.navigate("air") },
                onProfile = { navController.navigate("profile") },
                tipsViewModel = tipsViewModel
            )
        }

        composable("tips") {
            TipsScreen(
                viewModel = tipsViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable("air") {
            val vm: AirViewModel = viewModel(
                factory = AirViewModelFactory(AirModule.repository)
            )
            AirScreen(
                viewModel = vm,
                onBack = { navController.popBackStack() }
            )
        }

        // ➕ dentro do NavHost
        composable("profile") {
            ProfileScreen(
                onBack = { navController.popBackStack() },
                onLogout = {
                    navController.navigate("login") {
                        popUpTo(0)
                    }
                }
            )
        }

    }
}
