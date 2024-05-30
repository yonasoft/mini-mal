package com.yonasoft.minimal.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yonasoft.minimal.components.AnimeItemRow
import com.yonasoft.minimal.components.CircularProgress
import com.yonasoft.minimal.components.MiniButton
import com.yonasoft.minimal.navigation.Screen
import com.yonasoft.minimal.ui.theme.Blue1
import com.yonasoft.minimal.ui.theme.Blue2
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    rootNavController: NavController,
    homeViewModel: HomeViewModel,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    filler: String = ""
) {
    val coroutineScope = rememberCoroutineScope()

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Blue2)
                .padding(8.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Popular Airing",
                    color = Color.White
                )
                MiniButton(onClick = { rootNavController.navigate(Screen.RankingScreen.route) }, text = "more")
            }
            Spacer(modifier = Modifier.height(8.dp))
            if (homeViewModel.airRankingLoading) {
                CircularProgress(
                    boxModifier = Modifier
                        .fillMaxWidth()
                        .height(152.dp),
                    alignment = Alignment.Center,
                    indicatorModifier = Modifier.size(128.dp),
                    color = Blue1,
                    strokeWidth = 12.dp
                )
            } else {
                LazyRow(state = rememberLazyListState()) {
                    itemsIndexed(homeViewModel.airingRanking) { index, anime ->
                        if (index >= homeViewModel.airingRanking.size - 1 && !homeViewModel.airRankingLoading) {
                            LaunchedEffect(Unit) {
                                coroutineScope.launch {
                                    homeViewModel.loadAiringRanking()
                                }
                            }
                        }
                        AnimeItemRow(animeDetail = anime) {
                            rootNavController.navigate(
                                Screen.AnimeDetailScreen.withArgs(anime.id.toString())
                            )
                        }
                    }
                    if (homeViewModel.airRankingLoading) {
                        item {
                            CircularProgress(
                                boxModifier = Modifier
                                    .width(64.dp)
                                    .height(64.dp)
                                    .padding(16.dp),
                                alignment = Alignment.Center,
                                indicatorModifier = Modifier.size(32.dp),
                                color = Blue1,
                                strokeWidth = 8.dp
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Seasonal",
                    color = Color.White
                )
                MiniButton(onClick = { rootNavController.navigate(Screen.SeasonalScreen.route) }, text = "more")
            }
            Spacer(modifier = Modifier.height(8.dp))
            if (homeViewModel.seasonalLoading) {
                CircularProgress(
                    boxModifier = Modifier
                        .fillMaxWidth()
                        .height(152.dp),
                    alignment = Alignment.Center,
                    indicatorModifier = Modifier.size(128.dp),
                    color = Blue1,
                    strokeWidth = 12.dp
                )
            } else {
                LazyRow(state = rememberLazyListState()) {
                    itemsIndexed(homeViewModel.seasonal) { index, anime ->
                        if (index >= homeViewModel.seasonal.size - 2 && !homeViewModel.seasonalLoading) {
                            LaunchedEffect(Unit) {
                                coroutineScope.launch {
                                    homeViewModel.loadSeasonal()
                                }
                            }
                        }
                        AnimeItemRow(animeDetail = anime) {
                            rootNavController.navigate(
                                Screen.AnimeDetailScreen.withArgs(anime.id.toString())
                            )
                        }
                    }
                    if (homeViewModel.seasonalLoading) {
                        item {
                            CircularProgress(
                                boxModifier = Modifier
                                    .width(64.dp)
                                    .height(64.dp)
                                    .padding(16.dp),
                                alignment = Alignment.Center,
                                indicatorModifier = Modifier.size(32.dp),
                                color = Blue1,
                                strokeWidth = 8.dp
                            )
                        }
                    }
                }
            }
        }
    }
}
