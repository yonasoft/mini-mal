package com.yonasoft.minimal.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yonasoft.minimal.components.AnimeItemRow
import com.yonasoft.minimal.components.DrawerHeader
import com.yonasoft.minimal.components.HomeAppBar
import com.yonasoft.minimal.components.LoggedOutDrawerBody
import com.yonasoft.minimal.model.MenuItems
import com.yonasoft.minimal.ui.theme.Blue1
import com.yonasoft.minimal.ui.theme.Blue2
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            HomeAppBar(
                text = "lol",
                onSearch = {},
                navigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerContent = {
            DrawerHeader()
            LoggedOutDrawerBody(
                items = MenuItems.loggedOutItems,
                navController = navController,
                onClick = { navController.navigate(it.route) })
            //TODO: Change to if state for logged in vs logged out
        }
    ) {
        Surface(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Blue2)
                    .padding(8.dp)
                    .verticalScroll(rememberScrollState()),
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "Popular Airing",
                        color = Color.White
                    )
                    Card(modifier = Modifier.width(60.dp),  backgroundColor = Blue1, shape = CircleShape, elevation = 12.dp){Text(text = "more", textAlign = TextAlign.Center)}
                }
                Spacer(modifier = Modifier.height(8.dp))
                if (homeViewModel.airRankingLoading) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(152.dp),
                            color = Blue1,
                            strokeWidth = 12.dp,
                        )
                    }

                } else {
                    LazyRow {
                        items(homeViewModel.airingRanking) { item ->
                            AnimeItemRow(animeDetail = item, navController = navController) {

                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "Seasonal",
                    color = Color.White)
                Spacer(modifier = Modifier.height(8.dp))
                if (homeViewModel.seasonalLoading) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(152.dp),
                            color = Blue1,
                            strokeWidth = 12.dp,
                        )
                    }
                } else {
                    LazyRow {
                        items(homeViewModel.seasonal) { item ->
                            AnimeItemRow(
                                animeDetail = item,
                                navController = navController
                            ) {

                            }
                        }
                    }
                }
                //TODO: Add currently watching row and suggested row if they logged in
            }
        }
    }
}