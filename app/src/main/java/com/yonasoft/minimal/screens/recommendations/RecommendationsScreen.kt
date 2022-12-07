package com.yonasoft.minimal.screens.recommendations

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.yonasoft.minimal.components.AnimeItemColumn
import com.yonasoft.minimal.components.CircularProgress
import com.yonasoft.minimal.components.SimpleAppBar
import com.yonasoft.minimal.navigation.Screen
import com.yonasoft.minimal.ui.theme.Blue1

@Composable
fun RecommendationScreens(navController: NavHostController, animeId:Int, recommendationsViewModel: RecommendationsViewModel) {
    val recommendations = recommendationsViewModel.recommendations
    val loading = recommendationsViewModel.loading
    val title = recommendationsViewModel.title

    Scaffold(topBar = {
        SimpleAppBar(text = title) {
            navController.popBackStack()
        }
    }) {
        Surface(modifier = Modifier.padding(it)) {

            if(loading){
                CircularProgress(
                    boxModifier = Modifier.fillMaxSize(),
                    alignment = Alignment.Center,
                    indicatorModifier = Modifier.size(152.dp),
                    color = Blue1,
                    strokeWidth = 16.dp
                )
            } else {
                LazyColumn {
                    items(recommendations) { animeDetail ->
                        AnimeItemColumn(animeDetail = animeDetail) {
                            navController.navigate(Screen.AnimeDetailScreen.withArgs(animeDetail.id.toString()))
                        }
                    }
                }
            }
        }
    }
}