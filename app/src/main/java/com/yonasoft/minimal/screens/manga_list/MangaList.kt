package com.yonasoft.minimal.screens.manga_list

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.yonasoft.minimal.components.MessageScreen

@Composable
fun MangaListScreen(

    botNavController: NavController,
    rootNavController: NavController,) {
    MessageScreen(message ="Please login to see your personal Manga List")
}