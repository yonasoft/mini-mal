package com.yonasoft.minimal.screens.logins

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.yonasoft.minimal.navigation.Screen
import com.yonasoft.minimal.network.MALAuth.Companion.authUri
import com.yonasoft.minimal.ui.theme.Blue1
import com.yonasoft.minimal.ui.theme.Blue2


@Composable
fun LoginRequestScreen(navController: NavController) {
    val context = LocalContext.current
    var checked by rememberSaveable {
        mutableStateOf(false)
    }
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Blue1),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.5f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        //TODO: Login to MAL and navigate to Home
                        Login(context = context)
                        navController.navigate(Screen.HomeScreen.route)
                    }, modifier = Modifier.padding(bottom = 16.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Blue2),
                    border = BorderStroke(1.dp, Color.Black)
                ) {
                    Text(text = "Login")
                }
                Button(
                    onClick = {
                        navController.navigate(Screen.MainScreen.route)
                    },
                    modifier = Modifier.padding(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Blue2),
                    border = BorderStroke(1.dp, Color.Black)
                ) {
                    Text(text = "Skip")
                }
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.5f)
            ) {

//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(top = 84.dp),
//                    verticalAlignment = Alignment.Bottom
//                ) {
//                    Checkbox(checked = checked, onCheckedChange = { checked = !checked })
//                    Text(
//                        text = "Don't show at startup. You can still login from he side bar!",
//                        fontSize = 16.sp
//                    )
//                }
            }
        }
    }
}


fun Login(context: Context) {

    val customTabsServiceBuilder = CustomTabsIntent.Builder()

    customTabsServiceBuilder
        .build()
        .launchUrl(context, Uri.parse(authUri))

}



