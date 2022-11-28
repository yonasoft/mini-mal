package com.yonasoft.minimal.screens.search

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yonasoft.minimal.components.SearchAppBar

@Composable
fun SearchScreen(
    navController: NavController,
    initialSearch: String = "",
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        SearchAppBar(text = searchViewModel.searchQuery,
            navController = navController,
            onSearch = {},
            onTextChange = { searchViewModel.searchQuery = it })
    }
    ) {
        Surface(modifier = Modifier.padding(it)) {

        }
    }
}
