package com.yonasoft.minimal.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
        Image(
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Splash Screen"
        )
    }
    LaunchedEffect(key1 = true) {
        delay(2000L)
        //TODO: Check if logged in adn navigate to either login screen or home screen
        navController.navigate(Screen.HomeScreen.route) {
            popUpTo(Screen.SplashScreen.route) {
                inclusive = true
            }
        }
    }
}