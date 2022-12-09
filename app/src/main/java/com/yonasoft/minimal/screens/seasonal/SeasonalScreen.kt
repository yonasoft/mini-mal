package com.yonasoft.minimal.screens.seasonal

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.yonasoft.minimal.components.MessageScreen
import com.yonasoft.minimal.screens.rankings.RankingViewModel

@Composable
fun SeasonalScreen(
    navController: NavController,
    seasonalViewModel: RankingViewModel
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        MessageScreen(message = "TBA")
    }
}