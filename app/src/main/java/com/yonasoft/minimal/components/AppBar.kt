package com.yonasoft.minimal.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yonasoft.minimal.ui.theme.Blue1


@Composable
fun HomeAppBar(
    text: String,
    onSearch: () -> Unit,
    onCancel: () -> Unit,
    onTextChange: (String) -> Unit,
    navigationIconClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Blue1),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = navigationIconClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Toggle Drawer"
                )
            }
            SearchTextField(
                text = text,
                onSearch = { onSearch() },
                onCancel = { onCancel() },
                onTextChange = { onTextChange(it) })
        }
    }
}

@Composable
fun SearchAppBar(
    navController: NavController,
    text: String = "",
    onSearch: () -> Unit,
    onCancel: () -> Unit,
    onTextChange: (String) -> Unit,
    onNavigateBack: () -> Unit
) {
    Surface(
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Blue1),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = { onNavigateBack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Go Back"
                )
            }
            SearchTextField(
                text = text,
                onSearch = { onSearch() },
                onCancel = { onCancel() },
                onTextChange = { onTextChange(it) })
        }
    }
}

