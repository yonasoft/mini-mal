package com.yonasoft.minimal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.yonasoft.minimal.screens.home.HomeViewModel
import com.yonasoft.minimal.screens.logins.LoginRequestScreen
import com.yonasoft.minimal.screens.main.MainScreen
import com.yonasoft.minimal.screens.search.SearchScreen
import com.yonasoft.minimal.screens.search.SearchViewModel
import com.yonasoft.minimal.screens.splash.SplashScreen


@Composable
fun RootNavigation(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    searchViewModel: SearchViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {

        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Screen.MainScreen.route) {
            MainScreen(
                navController = navController,
                homeViewModel = homeViewModel,
                searchViewModel = searchViewModel
            )
        }

        composable(route = Screen.LoginRequestScreen.route) {
            LoginRequestScreen(navController = navController)
        }

        composable(
            route = Screen.SearchScreen.route
        ) {
            SearchScreen(
                navController = navController,
                searchViewModel = searchViewModel
            )
        }
    }
}

