package com.yonasoft.minimal.screens.anime_list

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.yonasoft.minimal.components.MessageScreen

@Composable
fun AnimeListScreen(    botNavController: NavController,
                        rootNavController: NavController,) {
    MessageScreen(message ="Please login to see your Personal Anime List")
}


