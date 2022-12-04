package com.yonasoft.minimal.navigation

sealed class Screen(val route:String){
    object SplashScreen: Screen("splash")
    object LoginRequestScreen:Screen("login_request")
    object AnimeListScreen:Screen("anime_list")
    object MangaListScreen:Screen("manga_list")
    object HomeScreen:Screen("home")
    object MainScreen:Screen("main")
    object SearchScreen:Screen("search")
    object AnimeDetailScreen:Screen("anime_detail")
    object RankingScreen:Screen("ranking")
    object SeasonalScreen:Screen("seasonal")

    fun withArgs(vararg args:String): String {
        return buildString{
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}

