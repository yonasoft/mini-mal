package com.yonasoft.minimal.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.yonasoft.minimal.components.*
import com.yonasoft.minimal.navigation.BottomNavigation
import com.yonasoft.minimal.navigation.Screen
import com.yonasoft.minimal.screens.home.HomeViewModel
import com.yonasoft.minimal.screens.search.SearchViewModel
import kotlinx.coroutines.launch


@Composable
fun MainScreen(navController:NavController,
               bottomNavController: NavHostController = rememberNavController(),
                homeViewModel: HomeViewModel,
               searchViewModel: SearchViewModel,
               ) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState,
        topBar = {

            HomeAppBar(
                text = searchViewModel.searchQuery,
                onSearch = {
                    searchViewModel.getAnimeList()
                    navController.navigate(
                        Screen.SearchScreen.route
                    )
                },
                onCancel = {searchViewModel.searchQuery = ""},
                navigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                },
                onTextChange = { searchViewModel.searchQuery = it }
            )
        },
        drawerContent = {
            DrawerHeader()
            LoggedOutDrawerBody(
                items = MenuItems.loggedOutItems,
                navController = navController,
                onClick = { navController.navigate(it.route) })
            //TODO: Change to if state for logged in vs logged out
        },
        bottomBar = { BottomBar(navController = bottomNavController) }) {

        Surface(modifier = Modifier.padding(it)) {
            BottomNavigation(navController = bottomNavController, homeViewModel = homeViewModel)
        }

    }
}