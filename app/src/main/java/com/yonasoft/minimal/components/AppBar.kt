package com.yonasoft.minimal.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@Composable
fun SimpleAppBar(
    text: String = "",
    onNavigateBack: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth(),
        title = {
            Text(
                text = text,
                fontSize = 20.sp,
                color = Color.White
            )
        },
        backgroundColor = Blue1,
        navigationIcon = {
            IconButton(onClick = { onNavigateBack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Go Back"
                )
            }
        }
    )
}

@Composable
fun RankingAppBar(
    title: String,
    onNavigateBack: () -> Unit,
    onSelect: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(modifier = Modifier,
        title = {
            Text(
                text = title,
                fontSize = 20.sp,
                color = Color.White
            )
        },
        backgroundColor = Blue1,
        navigationIcon = {
            IconButton(onClick = { onNavigateBack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Go Back")
            }
        },
        actions = {
            IconButton(onClick = { expanded = true }) {
                Icon(imageVector = Icons.Default.Sort, contentDescription = "Sort items")
                DropdownMenu(expanded = expanded,
                    onDismissRequest = { expanded = false }) {
                    DropdownMenuItem(onClick = { onSelect("All") }) {
                        Text(text = "All")
                    }
                    DropdownMenuItem(onClick = {
                        onSelect("Airing")
                        expanded = false
                    }) {
                        Text(text = "Airing")
                    }
                    DropdownMenuItem(onClick = {
                        onSelect("Upcoming")
                        expanded = false
                    }) {
                        Text(text = "Upcoming")
                    }
                    DropdownMenuItem(onClick = {
                        onSelect("TV")
                        expanded = false
                    }) {
                        Text(text = "TV")
                    }
                    DropdownMenuItem(onClick = {
                        onSelect("OVA")
                        expanded = false
                    }) {
                        Text(text = "OVA")
                    }
                    DropdownMenuItem(onClick = {
                        onSelect("Movie")
                        expanded = false
                    }) {
                        Text(text = "Movie")
                    }
                    DropdownMenuItem(onClick = {
                        onSelect("Special")
                        expanded = false
                    }) {
                        Text(text = "Special")
                    }
                    DropdownMenuItem(onClick = {
                        onSelect("By Popularity")
                        expanded = false
                    }) {
                        Text(text = "By Popularity")
                    }
                    DropdownMenuItem(onClick = {
                        onSelect("Favorite")
                        expanded = false
                    }) {
                        Text(text = "Favorite")
                    }
                }
            }
        }
    )
}

@Composable
fun SeasonalAppBar(
    title: String,
    onNavigateBack: () -> Unit,
    onSelect: (String) -> Unit
) {


    TopAppBar(modifier = Modifier,
        title = {
            Text(
                text = title,
                fontSize = 20.sp,
                color = Color.White
            )
        },
        backgroundColor = Blue1,
        navigationIcon = {
            IconButton(onClick = {
                onNavigateBack()
            }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Go Back")
            }
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Default.GridView, contentDescription = "Sort items")

            }
        }
    )
}
