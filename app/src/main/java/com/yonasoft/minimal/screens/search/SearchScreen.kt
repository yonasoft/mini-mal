package com.yonasoft.minimal.screens.search

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
import androidx.navigation.NavController
import com.yonasoft.minimal.components.AnimeItemColumn
import com.yonasoft.minimal.components.CircularProgress
import com.yonasoft.minimal.components.SearchAppBar
import com.yonasoft.minimal.navigation.Screen
import com.yonasoft.minimal.ui.theme.Blue1
import com.yonasoft.minimal.ui.theme.Blue2

@Composable
fun SearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel
) {

    Scaffold(topBar = {
        SearchAppBar(text = searchViewModel.searchQuery,
            onSearch = { searchViewModel.getAnimeList(searchViewModel.searchQuery) },
            onCancel = { searchViewModel.searchQuery = "" },
            onTextChange = { searchViewModel.searchQuery = it }
        ) {
            navController.popBackStack()
        }
    }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Blue2)
        ) {
            if (searchViewModel.searchLoading) {
                CircularProgress(
                    boxModifier = Modifier.fillMaxSize(),
                    alignment = Alignment.Center,
                    indicatorModifier = Modifier.size(160.dp),
                    color = Blue1,
                    strokeWidth = 12.dp
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .background(Blue2)
                        .fillMaxSize()
                ) {
                    items(searchViewModel.searchResult) { anime ->
                        AnimeItemColumn(animeDetail = anime,
                            onClick = {
                                navController.navigate(
                                    Screen.AnimeDetailScreen.withArgs(anime.id.toString())
                                )
                            })
                    }
                }
            }
        }
    }
}
