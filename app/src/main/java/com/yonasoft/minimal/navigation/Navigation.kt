package com.yonasoft.minimal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yonasoft.minimal.screens.LoginRequestScreen
import com.yonasoft.minimal.screens.anime_list.AnimeListScreen
import com.yonasoft.minimal.screens.home.HomeScreen
import com.yonasoft.minimal.screens.manga_list.MangaListScreen
import com.yonasoft.minimal.screens.search.SearchScreen
import com.yonasoft.minimal.screens.splash.SplashScreen


@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.LoginRequestScreen.route) {
            LoginRequestScreen(navController = navController)
        }

        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.AnimeListScreen.route) {
            AnimeListScreen(navController = navController)
        }
        composable(route = Screen.MangaListScreen.route) {
            MangaListScreen(navController)
        }
        composable(route = Screen.SearchScreen.route + "/{initial_search}",
            arguments = listOf(
                navArgument("?initial_search={initial_search}") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { entry ->
            SearchScreen(
                navController = navController,
                initialSearch = entry.arguments?.getString("initial_search")!!
            )
        }
    }
}
