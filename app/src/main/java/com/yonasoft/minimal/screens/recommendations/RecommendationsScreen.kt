package com.yonasoft.minimal.screens.recommendations

import androidx.compose.foundation.background
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
import com.yonasoft.minimal.components.*
import com.yonasoft.minimal.model.anime_detail_model.AnimeDetail
import com.yonasoft.minimal.navigation.Screen
import com.yonasoft.minimal.ui.theme.Blue1
import com.yonasoft.minimal.ui.theme.Blue2

@Composable
fun RecommendationScreens(
    navController: NavHostController,
    recommendationId: Int,
    recommendationType: Int,
    recommendationsViewModel: RecommendationsViewModel
) {
    val recommendations = when (recommendationsViewModel.recommendationType) {
        0 -> recommendationsViewModel.animeRecommendations
        1 -> recommendationsViewModel.mangaRecommendations
        else -> {
            emptyList<AnimeDetail>()
        }
    }
    val loading = recommendationsViewModel.loading
    val title = recommendationsViewModel.title

    Scaffold(topBar = {
        SimpleAppBar(text = title) {
            navController.popBackStack()
        }
    }) {
        Surface(modifier = Modifier.padding(it)) {

            if (loading) {
                CircularProgress(
                    boxModifier = Modifier.fillMaxSize(),
                    alignment = Alignment.Center,
                    indicatorModifier = Modifier.size(152.dp),
                    color = Blue1,
                    strokeWidth = 16.dp
                )
            } else {
                if (recommendations.isEmpty()) {
                    MessageScreen(message = "No Recommendations Currently")
                } else {
                    LazyColumn(modifier = Modifier.background(Blue2)) {
                        when (recommendationsViewModel.recommendationType) {
                            0 -> items(recommendationsViewModel.animeRecommendations) { animeDetail ->
                                AnimeItemColumn(animeDetail = animeDetail) {
                                    navController.navigate(
                                        Screen.AnimeDetailScreen.withArgs(
                                            animeDetail.id.toString()
                                        )
                                    )
                                }

                            }
                            1 -> items(recommendationsViewModel.mangaRecommendations) { mangaDetail ->
                               MangaItemColumn(mangaDetail = mangaDetail) {
                                    navController.navigate(
                                        Screen.MangaDetailScreen.withArgs(
                                            mangaDetail.id.toString()
                                        )
                                    )
                                }

                            }
                        }

                    }
                }
            }
        }
    }
}