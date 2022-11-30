package com.yonasoft.minimal.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yonasoft.minimal.components.AnimeItemRow
import com.yonasoft.minimal.components.CircularProgress
import com.yonasoft.minimal.ui.theme.Blue1
import com.yonasoft.minimal.ui.theme.Blue2

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel,
    //DO NOT remove the string parameter and the default value below. For an unknown reason, if it is removed the app will crash.
    filler:String = ""
) {
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
                Card(
                    modifier = Modifier
                        .width(60.dp)
                        .clickable { },
                    backgroundColor = Blue1,
                    shape = CircleShape,
                    elevation = 12.dp
                ) { Text(text = "more", textAlign = TextAlign.Center) }
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
                LazyRow {
                    items(homeViewModel.airingRanking) { item ->
                        AnimeItemRow(animeDetail = item) {

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
                Card(
                    modifier = Modifier
                        .width(60.dp)
                        .clickable { },
                    backgroundColor = Blue1,
                    shape = CircleShape,
                    elevation = 12.dp
                ) { Text(text = "more", textAlign = TextAlign.Center) }
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
                LazyRow {
                    items(homeViewModel.seasonal) { item ->
                        AnimeItemRow(
                            animeDetail = item,
                        ) {

                        }
                    }
                }
            }
            //TODO: Add currently watching row and recommended/suggested row if they logged in
        }
    }
}



