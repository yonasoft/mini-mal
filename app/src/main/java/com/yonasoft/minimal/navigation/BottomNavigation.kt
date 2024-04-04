package com.yonasoft.minimal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yonasoft.minimal.screens.home.HomeScreen
import com.yonasoft.minimal.screens.home.HomeViewModel

//Bottom navigation for bottom nav bar to navigate and used for screen within Main Screen
@Composable
fun BottomNavigation(
    rootNavHostController: NavHostController,
    botNavController: NavHostController = rememberNavController(),
    homeViewModel: HomeViewModel
) {

    NavHost(navController = botNavController, startDestination = Screen.HomeScreen.route) {

        composable(route = Screen.HomeScreen.route) {
            HomeScreen(rootNavController = rootNavHostController, homeViewModel = homeViewModel)
        }
    }
}