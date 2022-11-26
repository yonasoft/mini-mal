package com.yonasoft.minimal.model


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.yonasoft.minimal.navigation.Screen
import javax.inject.Singleton

abstract class MenuItem(
    open val title: String,
    open val contentDescription: String
)

data class MenuDestination(
    val route: String,
    override val title: String,
    val icon: ImageVector? = null,
    override val contentDescription: String,
) : MenuItem(title, contentDescription)


data class MenuSection(
    override var title: String,
    val icon: ImageVector? = null,
    override val contentDescription: String,
) : MenuItem(title, contentDescription)

@Singleton
object MenuItems {
    val loggedOutItems = listOf(
        MenuDestination(
            route = Screen.HomeScreen.route,
            title = "Home",
            icon = Icons.Default.Home,
            contentDescription = "Navigate to Home",
        ),
        MenuSection(
            title = "Discover",
            contentDescription = "Discover Section"
        ),
        MenuDestination(
            route = "",
            title = "News + Articles",
            icon = Icons.Default.Newspaper,
            contentDescription = "Navigate to News and Articles",
        ),

        MenuDestination(
            route = "",
            title = "Top",
            icon = Icons.Default.Leaderboard,
            contentDescription = "Navigate to Top Anime",
        ),
        MenuDestination(
            route = "",
            title = "Seasonal",
            icon = Icons.Default.DateRange,
            contentDescription = "Navigate to Seasonal Anime",
        ),
        MenuSection(
            title = "Account",
            contentDescription = "Account Section"
        ),
        MenuDestination(
            route = Screen.LoginRequestScreen.route,
            title = "Login",
            icon = Icons.Default.Login,
            contentDescription = "Navigate to Login",
        ),

        )
}

