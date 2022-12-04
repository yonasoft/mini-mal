package com.yonasoft.minimal.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.yonasoft.minimal.model.anime_detail_model.AnimeDetail
import com.yonasoft.minimal.ui.theme.Blue1
import java.util.*

@Composable
fun AnimeItemColumn(
    animeDetail: AnimeDetail,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .height(160.dp)
            .padding(8.dp),
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
                    .fillMaxWidth(.3f),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.fillMaxSize(),

                ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.5f)
                        .background(Blue1),
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(modifier = Modifier, contentAlignment = Center) {

                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                       text = animeDetail.title,
                            fontSize = 18.sp, color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(1f),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(text = "Score: ${animeDetail.mean}", fontSize = 14.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Episodes: ${animeDetail.num_episodes}", fontSize = 14.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Type: ${animeDetail.media_type.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.getDefault()
                            ) else it.toString()
                        }}"
                        , fontSize = 14.sp)
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = animeDetail.genres!!.joinToString(separator = ", ") { it.name },
                        fontSize = 16.sp, color = Color.Gray

                    )
                }
            }
        }

    }
}
