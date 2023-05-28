package com.yonasoft.minimal

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.yonasoft.minimal.ui.theme.MiniMALTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity:ComponentActivity() {
    private val viewModel:LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MiniMALTheme {
                AuthScreen(authenticationViewModel = viewModel)
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val code = intent?.data?.getQueryParameter("code")
        Log.d("auth", "code is $code")
        val uriState = intent?.data?.getQueryParameter("state")
        Log.d("auth", "uriState is $uriState, vm state is ${viewModel.state} ")
        if (code != null && uriState == viewModel.state) {
            viewModel.handleAuthorizationCode(code)
        }
        finish()
    }
}

@Composable
fun AuthScreen(authenticationViewModel: LoginViewModel) {

    val authorizationUrl = authenticationViewModel.authUrl
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(
        LocalContext.current, Uri.parse(authorizationUrl))
}