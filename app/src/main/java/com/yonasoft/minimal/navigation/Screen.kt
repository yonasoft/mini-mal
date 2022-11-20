package com.yonasoft.minimal.navigation

sealed class Screen(val route:String){
    object SplashScreen: Screen("splash")
    object OpeningLoginScreen:Screen("login_opening")
    object LoginScreen:Screen("login")
    object HomeScreen:Screen("home")
    object LoggedInHomeScreen:Screen("logged_in_home")
    object AnimeListScreen:Screen("anime_list")
    object MangaListScreen:Screen("manga_list")
}

