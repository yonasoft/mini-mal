@file:OptIn(ExperimentalPagerApi::class)

package com.yonasoft.minimal.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.yonasoft.minimal.components.AnimeItemColumn
import com.yonasoft.minimal.components.CircularProgress
import com.yonasoft.minimal.components.MangaItemColumn
import com.yonasoft.minimal.components.SearchAppBar
import com.yonasoft.minimal.navigation.Screen
import com.yonasoft.minimal.ui.theme.Blue1
import com.yonasoft.minimal.ui.theme.Blue2
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel
) {
    val scope = rememberCoroutineScope()
    val query = searchViewModel.searchQuery
    val previousQuery = searchViewModel.previousSearch

    Scaffold(topBar = {
        SearchAppBar(text = query,
            onSearch = {
                if (previousQuery != query) {
                    searchViewModel.onNewSearch()
                }
                searchViewModel.performSearch()
            },
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
            val pagerState = rememberPagerState(0)
            val pages = listOf("Anime", "Manga")

            Column(modifier = Modifier.fillMaxSize()) {

                ScrollableTabRow(
                    selectedTabIndex = pagerState.currentPage,
                    backgroundColor = Blue1,
                    contentColor = Color.White,
                    indicator = { tabPositions ->
                        TabRowDefaults.Indicator(
                            Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                        )
                    },
                ) {
                    // Add tabs for all of our pages
                    pages.forEachIndexed { index, title ->
                        Tab(
                            text = { Text(title) },
                            selected = pagerState.currentPage == index,
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            },
                        )
                    }
                }

                HorizontalPager(
                    modifier = Modifier.fillMaxSize(),
                    count = pages.size,
                    state = pagerState,
                ) { page ->
                    when (page) {
                        0 -> AnimeTab(
                            searchViewModel = searchViewModel, navController = navController
                        )

                        1 -> MangaTab(
                            searchViewModel = searchViewModel,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun AnimeTab(
    searchViewModel: SearchViewModel,
    navController: NavController
) {
    val animeResult = searchViewModel.animeResult
    val animeLoading = searchViewModel.animeLoading
    val scope = rememberCoroutineScope()

    if (animeLoading) {
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
                .fillMaxSize(),
            state = LazyListState()
        ) {
            itemsIndexed(animeResult) { index, anime ->
                if (index >= animeResult.size - 2 && !animeLoading) {
                    LaunchedEffect(Unit) {
                        scope.launch {
                            searchViewModel.getAnimeList()
                        }
                    }
                }
                AnimeItemColumn(
                    animeDetail = anime
                ) {
                    navController.navigate(
                        Screen.AnimeDetailScreen.withArgs(anime.id.toString())
                    )
                }
            }
        }
    }
}

@Composable
private fun MangaTab(
    searchViewModel: SearchViewModel,
    navController: NavController
) {

    val scope = rememberCoroutineScope()
    val mangaResult = searchViewModel.mangaResult
    val mangaLoading = searchViewModel.mangaLoading

    if (searchViewModel.mangaLoading) {
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
                .fillMaxSize(),
            rememberLazyListState()
        ) {

            itemsIndexed(mangaResult) { index, manga ->
                if (index >= mangaResult.size - 2 && !mangaLoading) {
                    LaunchedEffect(Unit) {
                        scope.launch {
                            searchViewModel.getMangaList()
                        }
                    }
                }
                MangaItemColumn(
                    mangaDetail = manga
                ) {
                    navController.navigate(Screen.MangaDetailScreen.withArgs(manga.id.toString()))
                }
            }
        }
    }
}