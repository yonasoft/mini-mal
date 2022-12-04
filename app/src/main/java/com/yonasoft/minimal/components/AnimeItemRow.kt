package com.yonasoft.minimal.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarViewWeek
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.yonasoft.minimal.model.anime_detail_model.AnimeDetail
import com.yonasoft.minimal.ui.theme.Blue1
import java.util.*


@Composable
fun AnimeItemRow(
    animeDetail: AnimeDetail,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(280.dp)
            .height(160.dp)
            .clickable { onClick() },
        backgroundColor = Color.White,
        elevation = 28.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = animeDetail.main_picture.medium,
                contentDescription = "Anime Main Picture",
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(.45f),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.fillMaxSize(),

                ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight(.5f)
                        .fillMaxWidth()
                        .background(
                            Blue1
                        )
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.Center),
                        text = animeDetail.title,
                        fontSize = 16.sp,
                        color = Color.White,
                    )
                }
                Row(Modifier.fillMaxWidth()) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Anime Rating",
                        tint = Color.Yellow
                    )
                    Text(text = animeDetail.mean.toString(), fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(imageVector = Icons.Default.Numbers, contentDescription = "Episodes")
                    Text(text = animeDetail.num_episodes.toString(), fontSize = 16.sp)
                }
                Text(
                    text = animeDetail.genres!!.joinToString(separator = ", ") { it.name },
                    fontSize = 12.sp, color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun PersonalAnimeItemRow(){

}