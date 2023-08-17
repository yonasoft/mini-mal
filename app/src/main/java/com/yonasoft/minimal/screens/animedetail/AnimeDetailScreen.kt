package com.yonasoft.minimal.screens.animedetail

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
import androidx.compose.ui.unit.TextUnit
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
                text = animeDetail?.title ?: "",
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
            if (!loadingDetail && animeDetail != null) {
                DetailContent(
                    navController = navController,
                    animeDetail = animeDetail
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
fun DetailContent(navController: NavController, animeDetail: AnimeDetail) {
    Column(
        modifier = Modifier.fillMaxSize().background(Blue2)
    ) {
        ImageWithBasicStats(animeDetail)
        DetailInfo(animeDetail)
        ExternalLinks(navController, animeDetail)
    }
}

@Composable
fun ImageWithBasicStats(animeDetail: AnimeDetail) {
    Row(modifier = Modifier.fillMaxSize()) {
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
}

@Composable
fun DetailInfo(animeDetail: AnimeDetail) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleText(text = animeDetail.title, fontSize = 28.sp)
        TitleText(text = "EN: ${animeDetail.alternative_titles.en}")
        TitleText(text = "JP: ${animeDetail.alternative_titles.ja}")
        GenresText(animeDetail)
        TitleText(text = "Synopsis", fontSize = 20.sp)
        NormalText(text = animeDetail.synopsis)
        StudiosText(animeDetail)
        NormalText(text = "Source: ${animeDetail.source}")
        NormalText(text = "Rating: ${animeDetail.rating}")
        BroadcastText(animeDetail)
    }
}

@Composable
fun ExternalLinks(navController: NavController, animeDetail: AnimeDetail) {
    val context = LocalContext.current

    SendElseWhere(
        text = "Recommendations",
        onClick = {
            navController.navigate(Screen.RecommendationsScreen.withArgs(animeDetail.id.toString(), "0"))
        }
    )

    SendElseWhere(
        text = "Open in Browser",
        onClick = {
            val uri = "https://myanimelist.net/anime/${animeDetail.id}/"
            CustomTabsIntent.Builder().build().launchUrl(context, Uri.parse(uri))
        }
    )
}

@Composable
fun StudiosText(animeDetail: AnimeDetail) {
    NormalText(text = "Studios: ${animeDetail.studios.joinToString(separator = ", ") { it.name }}")
}

@Composable
fun BroadcastText(animeDetail: AnimeDetail) {
    if (animeDetail.broadcast!=null) {
        NormalText(text = "Broadcast: ${animeDetail.broadcast.day_of_the_week}, ${animeDetail.broadcast.start_time}")
    }
}

@Composable
fun GenresText(animeDetail: AnimeDetail) {
    Text(
        text = animeDetail.genres!!.joinToString(separator = ", ") { it.name },
        textAlign = TextAlign.Center,
        color = Blue1,
        fontSize = 16.sp
    )
}

@Composable
fun NormalText(text: String) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        fontSize = 16.sp
    )
}


@Composable
fun TitleText(text: String, fontSize: TextUnit = 16.sp) {
    Text(
        text = text,
        fontSize = fontSize,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
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