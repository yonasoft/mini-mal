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

//Bottom navigation for bottom nav bar to navigate and used for screen within Main Screen
@Composable
fun BottomNavigation(
    rootNavHostController: NavHostController,
    botNavController: NavHostController = rememberNavController(),
    homeViewModel: HomeViewModel
) {

    NavHost(navController = botNavController, startDestination = Screen.HomeScreen.route) {

        composable(route = Screen.HomeScreen.route) {
            HomeScreen(rootNavController = rootNavHostController, botNavController = botNavController, homeViewModel = homeViewModel)
        }
        composable(route = Screen.AnimeListScreen.route) {
            AnimeListScreen(rootNavController = rootNavHostController, botNavController = botNavController)
        }
        composable(route = Screen.MangaListScreen.route) {
            MangaListScreen(rootNavController = rootNavHostController, botNavController = botNavController)
        }


    }
}