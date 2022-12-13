package com.yonasoft.minimal.screens.animedetail

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.yonasoft.minimal.components.CircularProgress
import com.yonasoft.minimal.components.SimpleAppBar
import com.yonasoft.minimal.model.anime_detail_model.AnimeDetail
import com.yonasoft.minimal.navigation.Screen
import com.yonasoft.minimal.ui.theme.Blue1
import com.yonasoft.minimal.ui.theme.Blue2
import java.util.*
import kotlin.math.ceil


@Composable
fun AnimeDetailScreen(
    navController: NavHostController,
    animeId: Int,
    detailViewModel: AnimeDetailViewModel
) {

    val loadingDetail = detailViewModel.loadingDetail
    val animeDetail = detailViewModel.animeDetail

    Scaffold(modifier = Modifier.padding(),
        topBar = {
            SimpleAppBar(
                text = if (!loadingDetail) {
                    animeDetail!!.title
                } else {
                    ""
                }
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
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            if (!loadingDetail) {
                Detail(
                    navController = navController,
                    context = LocalContext.current,
                    animeDetail = animeDetail!!
                )
            } else {
                CircularProgress(
                    boxModifier = Modifier.fillMaxSize(),
                    alignment = Alignment.Center,
                    indicatorModifier = Modifier.size(152.dp),
                    color = Blue1,
                    strokeWidth = 16.dp
                )
            }
        }

    }
}


@Composable
fun Detail(
    navController: NavController,
    modifier: Modifier = Modifier,
    context: Context,
    animeDetail: AnimeDetail
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Blue2)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(.45f)
                    .fillMaxHeight(),
                model = animeDetail.main_picture.large,
                contentDescription = "Anime Picture",
                contentScale = ContentScale.FillWidth
            )

            BasicStats(animeDetail)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            text = animeDetail.title,
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )


        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "EN: ${animeDetail.alternative_titles.en}",
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "JP: ${animeDetail.alternative_titles.ja}",
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )


        Text(
            modifier = Modifier.fillMaxWidth(),
            text = animeDetail.genres!!.joinToString(separator = ", ") { it.name },
            textAlign = TextAlign.Center,
            color = Blue1,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            text = "Synopsis",
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            text = animeDetail.synopsis,
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Studios: ${animeDetail.studios.joinToString(separator = ", ") { it.name }}",
            fontSize = 20.sp, color = Color.White
        )

        Text(
            text = "Source: ${animeDetail.source}",
            fontSize = 20.sp, color = Color.White
        )
        Text(
            text = "Rating: ${animeDetail.rating}",
            fontSize = 20.sp, color = Color.White
        )
        if (animeDetail.broadcast!= null) {
            Text(
                text = "Broadcast: ${animeDetail.broadcast.day_of_the_week}, ${animeDetail.broadcast.start_time}",
                fontSize = 20.sp, color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        SendElseWhere(text = "Recommendations",
            onClick = {
                navController.navigate(Screen.RecommendationsScreen.withArgs(animeDetail.id.toString(),"0"))
            })
        Spacer(modifier = Modifier.height(12.dp))
        SendElseWhere(text = "Open in Browser",
            onClick = {
                val uri = "https://myanimelist.net/anime/${animeDetail.id}/"
                CustomTabsIntent.Builder().build().launchUrl(context, Uri.parse(uri))
            })
    }
}

@Composable
private fun SendElseWhere(text: String, onClick: () -> Unit) {
    Divider(modifier = Modifier.height(1.dp), color = Color.White)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text, color = Blue1, fontSize = 16.sp)
        IconButton(onClick = { onClick() }) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Go to Recommendations"
            )
        }
    }
    Divider(modifier = Modifier.height(1.dp), color = Color.White)
}


@Composable
private fun BasicStats(animeDetail: AnimeDetail) {
    Column(
        modifier = Modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.Default.Star,
                contentDescription = "Rating",
                tint = Color.White,
            )
            Text(
                text = animeDetail.mean.toString(),
                color = Color.White,
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            )
        }

        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Rank:\n#${animeDetail.rank}",
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Popularity:\n#" + "%,d".format(animeDetail.popularity),
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Members:\n" + "%,d".format(animeDetail.num_list_users),
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Favorites:\n" + "%,d".format(animeDetail.num_scoring_users),
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Type:\n${
                        animeDetail.media_type.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.getDefault()
                            ) else it.toString()
                        }
                    }",
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Season:\n${animeDetail.start_season.year}",
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Episodes:\n${animeDetail.num_episodes}",
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Duration:\n${ceil((animeDetail.average_episode_duration / 60).toDouble()).toInt()} min",
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}