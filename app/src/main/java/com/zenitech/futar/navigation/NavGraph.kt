package com.zenitech.futar.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zenitech.futar.feature.boarding.BoardingScreen
import com.zenitech.futar.feature.home.HomeScreen
import com.zenitech.futar.feature.boarding.login.LoginScreen
import com.zenitech.futar.feature.boarding.traffic_number.TrafficNumberScreen
import com.zenitech.futar.feature.boarding.welcome.WelcomeScreen


@ExperimentalMaterial3Api
@Composable
fun NavGraph(
    navController: NavHostController,
    onStatusBarChange: (String) -> Unit,
    onLoggedInChange: (Boolean) -> Unit
) {
    NavHost(navController, startDestination = Screen.Boarding) {

        composable<Screen.Boarding> {

            onStatusBarChange("Kérem, válasszon felületet!")
            BoardingScreen(
                onStatusBarChange = {
                    onStatusBarChange(it)
                },
                onNavigateToHome = {
                    navController.navigate(Screen.Home)
                }
            )
        }

        composable<Screen.Home> {

            onLoggedInChange(true)
            onStatusBarChange("Új üzenet érkezett!")
            HomeScreen()
        }

    }
}

