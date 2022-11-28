package com.yonasoft.minimal.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yonasoft.minimal.components.*
import com.yonasoft.minimal.model.MenuItems
import com.yonasoft.minimal.navigation.Screen
import com.yonasoft.minimal.ui.theme.Blue1
import com.yonasoft.minimal.ui.theme.Blue2
import kotlinx.coroutines.launch

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
            //Passing an empty string as an arg when navigating leads to an error.
            //Multiple alternatives and work-arounds were attempted but what you see below is best option out of them.
            //A string with just a space is passed and then SearchViewModel will initial based on if the arg is " " or something else(User input)
            val navigationLocation =  Screen.SearchScreen.withArgs(homeViewModel.searchQuery.ifEmpty { " " })
            HomeAppBar(

                text = homeViewModel.searchQuery,
                onSearch = {
                    navController.navigate(
                        navigationLocation
                    )
                    homeViewModel.searchQuery = ""
                },
                navigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                },
                onTextChange = { homeViewModel.searchQuery = it }
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
                        boxModifier = Modifier.fillMaxWidth().height(152.dp),
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
                        boxModifier = Modifier.fillMaxWidth().height(152.dp),
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
}

