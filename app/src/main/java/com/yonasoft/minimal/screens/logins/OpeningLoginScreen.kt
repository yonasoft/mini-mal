package com.yonasoft.minimal.screens

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.yonasoft.minimal.navigation.Screen
import com.yonasoft.minimal.ui.theme.Blue1
import com.yonasoft.minimal.ui.theme.Blue2


@Composable
fun OpeningLoginScreen(navController: NavController) {
    val codeChallenge = "47DEQpj8HBSa-_TImW-5JCeuQeRkm5NMpJWZG3hSuFU"
    val codeVerifier =
        "-sQm3U3pEJeS~w2-IKJxBR-gVyrbS_B~M7H3sCtO9gYLNoAF9~HnNmIoTQ7AumgAQdhTCRVvgWdlP2ouIq_Ly.0LO3YxgX4fbxudbdf8K5k6GCXM69H3lD0l.MRjX0Fo"

    Surface(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom) {

            WebViewLogin(url = "www.google.com", modifier = Modifier.weight(1f))
            SkipQuestion(
                navController = navController,
                Modifier
                    .fillMaxWidth()
                    .background(Blue1)
                    .padding(4.dp),
                horizontalArrangement = Arrangement.End
                ) {
                navController.navigate(Screen.HomeScreen.route)
            }
        }
    }
}

@Composable
fun WebViewLogin(url: String, modifier: Modifier) {

    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    Box(modifier = modifier) {
        AndroidView(factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        }, update = {
            it.loadUrl(url)
        })
    }
}

@Composable
fun SkipQuestion(
    navController: NavController,
    modifier: Modifier,
    horizontalArrangement: Arrangement.Horizontal,
    onClickAction: () -> Unit) {
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement
    ) {
        Button(colors = ButtonDefaults.buttonColors(backgroundColor = Blue2),
            onClick = {
                onClickAction()
            }
        ) {
            Text(text = "Skip", color = Color.White)
        }
    }
}