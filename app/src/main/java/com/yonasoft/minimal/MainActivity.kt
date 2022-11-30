package com.yonasoft.minimal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.yonasoft.minimal.navigation.RootNavigation
import com.yonasoft.minimal.screens.home.HomeViewModel
import com.yonasoft.minimal.screens.search.SearchViewModel
import com.yonasoft.minimal.ui.theme.MiniMALTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MiniMALTheme {
                MiniMALApp()
            }
        }
    }
}

@Composable
fun MiniMALApp() {
    val navController = rememberNavController()
    val homeViewModel:HomeViewModel = viewModel()
    val searchViewModel:SearchViewModel = viewModel()
    RootNavigation(navController = navController, homeViewModel = homeViewModel, searchViewModel = searchViewModel)
}