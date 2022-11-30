package com.yonasoft.minimal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yonasoft.minimal.screens.anime_list.AnimeListScreen
import com.yonasoft.minimal.screens.home.HomeScreen
import com.yonasoft.minimal.screens.home.HomeViewModel
import com.yonasoft.minimal.screens.manga_list.MangaListScreen


@Composable
fun BottomNavigation(navController:NavHostController = rememberNavController(), homeViewModel: HomeViewModel) {

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {

        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController, homeViewModel = homeViewModel)
        }
        composable(route = Screen.AnimeListScreen.route) {
            AnimeListScreen(navController = navController)
        }
        composable(route = Screen.MangaListScreen.route) {
            MangaListScreen(navController = navController)
        }

    }
}