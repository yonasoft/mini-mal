package com.yonasoft.minimal.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
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
import com.yonasoft.minimal.screens.recommendations.RecommendationScreens
import com.yonasoft.minimal.screens.search.SearchScreen
import com.yonasoft.minimal.screens.search.SearchViewModel
import com.yonasoft.minimal.screens.seasonal.SeasonalScreen
import com.yonasoft.minimal.screens.splash.SplashScreen

//Root Navigation for the entire screen
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
        //Anime id needed to access details
        composable(route = Screen.AnimeDetailScreen.route+"/{anime_id}",
            arguments = listOf(navArgument("anime_id") {
                type = NavType.IntType
            }
            )

        ) { entry ->
            entry.arguments?.getInt("anime_id")?.let { AnimeDetailScreen(navController = rootNavController, animeId = it, detailViewModel = hiltViewModel()) }
        }
        //Anime id needed to access details and its recommended animes
        composable(route = Screen.RecommendationsScreen.route+"/{anime_id2}",
            arguments = listOf(navArgument("anime_id2") {
                type = NavType.IntType
            }
            )
        ) { entry ->
            entry.arguments?.getInt("anime_id2")?.let { RecommendationScreens(navController = rootNavController, animeId = it, recommendationsViewModel = hiltViewModel()) }
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
            RankingScreen(navController = rootNavController, rankingViewModel = hiltViewModel())
        }
        composable(route = Screen.SeasonalScreen.route) {
            SeasonalScreen(navController = rootNavController, seasonalViewModel = hiltViewModel())
        }
    }
}

