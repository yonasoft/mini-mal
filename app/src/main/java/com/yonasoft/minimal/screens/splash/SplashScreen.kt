package com.yonasoft.minimal.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.yonasoft.minimal.R
import com.yonasoft.minimal.navigation.Screen
import com.yonasoft.minimal.ui.theme.Blue1
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .background(color = Blue1)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(R.drawable.logo),
            contentDescription = "App Logo")
    }
    LaunchedEffect(key1 = true) {
        delay(1800L)
        navController.navigate(Screen.OpeningLoginScreen.route)
    }
}