package com.yonasoft.minimal.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yonasoft.minimal.components.DrawerHeader
import com.yonasoft.minimal.components.HomeAppBar
import com.yonasoft.minimal.components.LoggedOutDrawerBody
import com.yonasoft.minimal.model.MenuItems
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController,
                homeViewModel: HomeViewModel = hiltViewModel()
               ) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            HomeAppBar(
                text = "lol",
                onSearch = {},
                navigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerContent = {
            DrawerHeader()
            LoggedOutDrawerBody(
                items = MenuItems.loggedOutItems,
                navController = navController,
                onClick = { navController.navigate(it.route) })
            //TODO: Change to if state for logged in vs logged out
        }
    ) {
        Surface(modifier = Modifier.padding(it)) {
            LazyRow {
                items(homeViewModel.airingRanking){ item->
                    Text(item.title)

                }
            }
        }
    }
}