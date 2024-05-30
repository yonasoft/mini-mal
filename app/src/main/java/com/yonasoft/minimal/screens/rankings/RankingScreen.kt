package com.yonasoft.minimal.screens.rankings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.yonasoft.minimal.components.AnimeItemColumn
import com.yonasoft.minimal.components.CircularProgress
import com.yonasoft.minimal.components.RankingAppBar
import com.yonasoft.minimal.navigation.Screen
import com.yonasoft.minimal.ui.theme.Blue1
import com.yonasoft.minimal.ui.theme.Blue2
import kotlinx.coroutines.launch


@Composable
fun RankingScreen(
    navController: NavController,
    rankingViewModel: RankingViewModel
) {
    val rankingList = rankingViewModel.rankingList
    val loading = rankingViewModel.loading
    val currentRankingType = rankingViewModel.currentRankingType
    val title = "Top: $currentRankingType"

    Scaffold(
        modifier = Modifier.padding(),
        topBar = {
            RankingAppBar(title = title,
                onNavigateBack = { navController.popBackStack() },
                onSelect = { rankingViewModel.getRanking(it) })
        }
    ) {
        Surface(modifier = Modifier.padding(it)) {
            if (loading) {
                CircularProgress(
                    boxModifier = Modifier.fillMaxSize(),
                    alignment = Alignment.Center,
                    indicatorModifier = Modifier.size(160.dp),
                    color = Blue1,
                    strokeWidth = 12.dp
                )
            } else {
                Rankings(rankingViewModel = rankingViewModel, navController = navController)
            }
        }
    }
}

@Composable
fun Rankings(rankingViewModel: RankingViewModel, navController: NavController) {
    val scope = rememberCoroutineScope()
    val rankingList = rankingViewModel.rankingList
    val loading = rankingViewModel.loading

    LazyColumn(modifier = Modifier.background(Blue2), rememberLazyListState()) {
        itemsIndexed(rankingViewModel.rankingList) { index, animeDetail ->
            if (index >= rankingList.size - 2 && !loading) {
                LaunchedEffect(Unit) {
                    scope.launch {
                        rankingViewModel.getRanking()
                    }
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "# ${index + 1}",
                    fontSize = 20.sp,
                    color = Color.White
                )
                AnimeItemColumn(animeDetail = animeDetail) {
                    navController.navigate(Screen.AnimeDetailScreen.withArgs(animeDetail.id.toString()))
                }
            }
        }
    }
}