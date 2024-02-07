package com.yonasoft.minimal.components


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
        MenuSection(
            title = "Discover",
            contentDescription = "Discover Section"
        ),

        MenuDestination(
            route = Screen.RankingScreen.route,
            title = "Top",
            icon = Icons.Default.Leaderboard,
            contentDescription = "Navigate to Top Anime",
        ),
        MenuDestination(
            route = Screen.SeasonalScreen.route,
            title = "Seasonal",
            icon = Icons.Default.DateRange,
            contentDescription = "Navigate to Seasonal Anime",
        ),
        )
}

