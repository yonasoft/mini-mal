package com.yonasoft.minimal.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.yonasoft.minimal.screens.animedetail.AnimeDetailScreen
import com.yonasoft.minimal.screens.home.HomeViewModel
import com.yonasoft.minimal.screens.logins.LoginRequestScreen
import com.yonasoft.minimal.screens.main.MainScreen
import com.yonasoft.minimal.screens.rankings.RankingScreen
import com.yonasoft.minimal.screens.search.SearchScreen
import com.yonasoft.minimal.screens.search.SearchViewModel
import com.yonasoft.minimal.screens.seasonal.SeasonalScreen
import com.yonasoft.minimal.screens.splash.SplashScreen


@Composable
fun RootNavigation(
    rootNavController: NavHostController,
    homeViewModel: HomeViewModel,
    searchViewModel: SearchViewModel,
) {
    NavHost(
        navController = rootNavController,
        startDestination = Screen.SplashScreen.route
    ) {

        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = rootNavController)
        }

        composable(route = Screen.MainScreen.route) {
            MainScreen(
                rootNavController = rootNavController,
                homeViewModel = homeViewModel,
                searchViewModel = searchViewModel
            )
        }

        composable(route = Screen.LoginRequestScreen.route) {
            LoginRequestScreen(navController = rootNavController)
        }

        composable(route = Screen.AnimeDetailScreen.route+"/{anime_id}",
            arguments = listOf(navArgument("anime_id") {
                type = NavType.IntType
            }
            )

        ) { entry ->
            entry.arguments?.getInt("anime_id")?.let { AnimeDetailScreen(navController = rootNavController, animeId = it, detailViewModel = hiltViewModel()) }
        }

        composable(
            route = Screen.SearchScreen.route
        ) {
            SearchScreen(
                navController = rootNavController,
                searchViewModel = searchViewModel
            )
        }
        composable(route = Screen.RankingScreen.route) {
            RankingScreen()
        }
        composable(route = Screen.SeasonalScreen.route) {
            SeasonalScreen()
        }
    }
}

