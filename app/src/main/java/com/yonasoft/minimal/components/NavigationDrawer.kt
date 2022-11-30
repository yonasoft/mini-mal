package com.yonasoft.minimal.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yonasoft.minimal.R

@Composable
fun LoggedOutDrawerBody(
    navController: NavController,
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    onClick: (MenuDestination) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(items) { item ->
            if (item is MenuDestination) {
                Row(modifier = modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .clickable {
                        onClick(item)
                    }
                ) {
                    item.icon?.let { it ->
                        Icon(
                            imageVector = it,
                            contentDescription = item.contentDescription
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = item.title,
                        modifier = Modifier.weight(1f)
                    )
                }
            } else {
                //TODO: Login/Logout
                Row(modifier = Modifier.padding(top = 12.dp)) {
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = item.title,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun LoggedInDrawerBody() {

}

@Preview
@Composable
fun DrawerHeader(

) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Navigation Drawer Header"
        )
    }
}

