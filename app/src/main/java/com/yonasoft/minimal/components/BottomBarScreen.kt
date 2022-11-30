package com.yonasoft.minimal.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Tv
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.yonasoft.minimal.navigation.Screen
import com.yonasoft.minimal.ui.theme.Blue1

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val Icon: ImageVector
) {
    object Home : BottomBarScreen(
        route = Screen.HomeScreen.route,
        title = "Home",
        Icon = Icons.Default.Home
    )

    object MyAnime : BottomBarScreen(
        route = Screen.AnimeListScreen.route,
        title = "My Anime",
        Icon = Icons.Default.Tv
    )

    object MyManga : BottomBarScreen(
        route = Screen.MangaListScreen.route,
        title = "My Manga",
        Icon = Icons.Default.Book
    )
}

@Composable
fun BottomBar(navController: NavController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.MyAnime,
        BottomBarScreen.MyManga
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(backgroundColor = Blue1, elevation = 24.dp) {
        screens.forEach { screen ->
            if (currentDestination != null) {
                AddBottomItem(screen = screen, currentDestination = currentDestination, navController = navController)
            }
        }
    }
}

@Composable
fun RowScope.AddBottomItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination,
    navController: NavController
) {
    BottomNavigationItem(label = {
        Text(text = screen.title)
    },
        icon = {
            Icon(imageVector = screen.Icon,
                contentDescription = "Bottom nav icon",
                )
        },
        selected = currentDestination.hierarchy.any { it.route == screen.route },
        onClick = {
            navController.navigate(screen.route)
        },

    )
}