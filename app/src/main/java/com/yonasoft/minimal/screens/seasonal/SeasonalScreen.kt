package com.yonasoft.minimal.screens.seasonal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yonasoft.minimal.components.AnimeItemColumn
import com.yonasoft.minimal.components.CircularProgress
import com.yonasoft.minimal.components.SeasonalAppBar
import com.yonasoft.minimal.navigation.Screen
import com.yonasoft.minimal.ui.theme.Blue1
import com.yonasoft.minimal.ui.theme.Blue2
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun SeasonalScreen(
    navController: NavController,
    seasonalViewModel: SeasonalViewModel
) {
    val season = seasonalViewModel.season
    val year = seasonalViewModel.year
    val seasonalList = seasonalViewModel.seasonalList
    val loading = seasonalViewModel.loading
    var title by remember {
        mutableStateOf("${season.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }} $year")
    }
    val scope = rememberCoroutineScope()
    
    Scaffold(modifier = Modifier.padding(),
        topBar = {
            SeasonalAppBar(
                title = title ,
                onNavigateBack = { navController.popBackStack() },
                initialSeason = season,
                initialYear = year,
                onOk = {
                        s, y ->
                    seasonalViewModel.getSeasonal(s,y)
                    title = "${s.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }} $y"
                    seasonalViewModel.season = s
                    seasonalViewModel.year = y
                }
            )
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
                LazyColumn(modifier = Modifier.background(Blue2), state = rememberLazyListState()) {
                    itemsIndexed(seasonalList) { index, animeDetail ->
                        if(index >= seasonalList.size-2 && !loading) {
                            LaunchedEffect(Unit) {
                                scope.launch {
                                    seasonalViewModel.getSeasonal()
                                }
                            }
                        }
                        AnimeItemColumn(animeDetail = animeDetail) {
                            navController.navigate(Screen.AnimeDetailScreen.withArgs(animeDetail.id.toString()))
                        }
                    }
                }
            }
        }
    }
}