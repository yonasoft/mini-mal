package com.yonasoft.minimal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yonasoft.minimal.screens.OpeningLoginScreen
import com.yonasoft.minimal.screens.splash.SplashScreen


@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route){
        composable(route = Screen.SplashScreen.route){
            SplashScreen(navController = navController)
        }
        composable(route = Screen.OpeningLoginScreen.route){
            OpeningLoginScreen(navController = navController)
        }
        composable(route = Screen.LoginScreen.route){

        }
        composable(route = Screen.HomeScreen.route){

        }
        composable(route = Screen.LoggedInHomeScreen.route){

        }
        composable(route = Screen.AnimeListScreen.route){

        }
        composable(route = Screen.MangaListScreen.route){

        }
    }
}
