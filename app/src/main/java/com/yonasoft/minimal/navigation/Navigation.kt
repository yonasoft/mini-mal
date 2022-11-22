package com.yonasoft.minimal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yonasoft.minimal.screens.LoginRequestScreen
import com.yonasoft.minimal.screens.anime_list.AnimeListScreen
import com.yonasoft.minimal.screens.home.HomeScreen
import com.yonasoft.minimal.screens.manga_list.MangaListScreen
import com.yonasoft.minimal.screens.splash.SplashScreen


@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route){
        composable(route = Screen.SplashScreen.route){
            SplashScreen(navController = navController)
        }
        composable(route = Screen.LoginRequestScreen.route){
            LoginRequestScreen(navController = navController)
        }

        composable(route = Screen.HomeScreen.route){
            HomeScreen(navController = navController)
        }
        composable(route = Screen.AnimeListScreen.route){
            AnimeListScreen(navController = navController)
        }
        composable(route = Screen.MangaListScreen.route){
            MangaListScreen(navController)
        }
    }
}
