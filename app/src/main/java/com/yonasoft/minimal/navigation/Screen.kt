package com.yonasoft.minimal.navigation

sealed class Screen(val route:String){
    object SplashScreen: Screen("splash")
    object LoginRequestScreen:Screen("login_request")
    object AnimeListScreen:Screen("anime_list")
    object MangaListScreen:Screen("manga_list")
    object HomeScreen:Screen("home")
    object SearchScreen:Screen("search")

    fun withArgs(vararg args:String): String {
        return buildString{
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}

