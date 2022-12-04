package com.yonasoft.minimal.screens.seasonal

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yonasoft.minimal.components.MessageScreen

@Composable
fun SeasonalScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        MessageScreen(message = "TBA")
    }
}