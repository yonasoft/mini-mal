package com.yonasoft.minimal.screens.main

import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.yonasoft.minimal.AuthenticationActivity
import com.yonasoft.minimal.components.*
import com.yonasoft.minimal.navigation.BottomNavigation
import com.yonasoft.minimal.navigation.Screen
import com.yonasoft.minimal.screens.home.HomeViewModel
import com.yonasoft.minimal.screens.search.SearchViewModel
import kotlinx.coroutines.launch


@Composable
fun MainScreen(
    rootNavController: NavHostController,
    bottomNavController: NavHostController = rememberNavController(),
    homeViewModel: HomeViewModel,
    searchViewModel: SearchViewModel,
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val context =  LocalContext.current

    Scaffold(scaffoldState = scaffoldState,
        topBar = {

            HomeAppBar(
                text = searchViewModel.searchQuery,
                onSearch = {
                    searchViewModel.getAnimeList()
                    searchViewModel.getMangaList()
                    rootNavController.navigate(
                        Screen.SearchScreen.route
                    )
                },
                onCancel = { searchViewModel.searchQuery = "" },
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
                navController = rootNavController,
                onClick = {
                    if (it.title != "Login") {
                        rootNavController.navigate(it.route)
                    } else {
                        context.startActivity(Intent(context, AuthenticationActivity::class.java))
                    }
                })
            //TODO: Change to if state for logged in vs logged out
        },
        bottomBar = { BottomBar(navController = bottomNavController) }) {

        Surface(modifier = Modifier.padding(it)) {
            BottomNavigation(
                rootNavHostController = rootNavController,
                botNavController = bottomNavController,
                homeViewModel = homeViewModel
            )
        }

    }
}