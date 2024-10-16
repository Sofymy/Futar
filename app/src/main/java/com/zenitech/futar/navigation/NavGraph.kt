package com.zenitech.futar.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zenitech.futar.feature.boarding.BoardingScreen
import com.zenitech.futar.feature.home.HomeScreen


@ExperimentalMaterial3Api
@Composable
fun NavGraph(
    navController: NavHostController,
    onStatusBarChange: (String) -> Unit,
    onLoggedInChange: (Boolean) -> Unit,
    brightness: Float,
    onBrightnessChange: (Float) -> Unit,
    onRazziaChange: () -> Unit,
    razzia: Boolean
) {
    NavHost(navController, startDestination = Screen.Boarding) {

        composable<Screen.Boarding> {

            onLoggedInChange(false)
            onStatusBarChange("Kérem, válasszon felületet!")
            BoardingScreen(
                onStatusBarChange = {
                    onStatusBarChange(it)
                },
                onNavigateToHome = {
                    navController.navigate(Screen.Home){
                        popUpTo(Screen.Boarding){
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<Screen.Home> {

            onLoggedInChange(true)
            onStatusBarChange("Új üzenet érkezett!")
            HomeScreen(
                onBrightnessChange = onBrightnessChange,
                brightness = brightness,
                razzia = razzia,
                onRazziaChange = onRazziaChange,
                onLogout = {
                    navController.navigate(Screen.Boarding) {
                        popUpTo(Screen.Home) {
                            inclusive = true
                        }
                    }
                }
            )
        }

    }
}

