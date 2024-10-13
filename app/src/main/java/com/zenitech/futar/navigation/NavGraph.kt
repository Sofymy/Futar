package com.zenitech.futar.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
    NavHost(navController, startDestination = Screen.Welcome) {

        composable<Screen.Welcome> {

            onStatusBarChange("Kérem, válasszon felületet!")
            WelcomeScreen(
                onNavigateToLoginScreen = {
                    navController.navigate(Screen.Login)
                }
            )
        }


        composable<Screen.Login> {

            onStatusBarChange("Kérem, adja meg az azonosítóját!")
            LoginScreen(
                onNavigateToTrafficNumberScreen = {
                    navController.navigate(Screen.TrafficNumber)
                }
            )
        }

        composable<Screen.TrafficNumber> {

            onStatusBarChange("Kérem, adja meg a forgalmi számot!")
            TrafficNumberScreen(
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

