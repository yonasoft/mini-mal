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
import com.yonasoft.minimal.screens.manga_detail.MangaDetailScreen
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
        composable(route = Screen.AnimeDetailScreen.route + "/{anime_id}",
            arguments = listOf(navArgument("anime_id") {
                type = NavType.IntType
            }
            )

        ) { entry ->
            entry.arguments?.getInt("anime_id")?.let {
                AnimeDetailScreen(
                    navController = rootNavController,
                    animeId = it,
                    detailViewModel = hiltViewModel()
                )
            }
        }

        composable(route = Screen.MangaDetailScreen.route + "/{manga_id}",
            arguments = listOf(navArgument("manga_id") {
                type = NavType.IntType
            },
                navArgument("manga_id") {
                    type = NavType.IntType
                }
            )
        ) { entry ->
            entry.arguments?.getInt("manga_id")?.let {
                MangaDetailScreen(
                    navController = rootNavController,
                    mangaId = it,
                    detailViewModel = hiltViewModel()
                )
            }
        }

        composable(route = Screen.RecommendationsScreen.route + "/{rec_id}/{rec_type}",
            arguments = listOf(navArgument("rec_id") {
                type = NavType.IntType
            },
                navArgument("rec_type") {
                    type = NavType.IntType
                }
            )
        ) { entry ->
            RecommendationScreens(
                navController = rootNavController,
                recommendationId = entry.arguments!!.getInt("rec_id"),
                recommendationType = entry.arguments!!.getInt("rec_type"),
                recommendationsViewModel = hiltViewModel()
            )
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

