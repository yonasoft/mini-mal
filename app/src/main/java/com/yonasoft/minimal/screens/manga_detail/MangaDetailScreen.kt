package com.yonasoft.minimal.screens.manga_detail

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
import com.yonasoft.minimal.model.manga_detail_model.MangaDetail
import com.yonasoft.minimal.navigation.Screen
import com.yonasoft.minimal.ui.theme.Blue1
import com.yonasoft.minimal.ui.theme.Blue2
import java.util.*


@Composable
fun MangaDetailScreen(
    navController: NavHostController,
    mangaId: Int,
    detailViewModel: MangaDetailViewModel
) {

    val loadingDetail = detailViewModel.loadingDetail
    val mangaDetail = detailViewModel.mangaDetail

    Scaffold(modifier = Modifier.padding(),
        topBar = {
            SimpleAppBar(
                text = if (!loadingDetail) {
                    mangaDetail!!.title
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
                    mangaDetail = mangaDetail!!
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
    mangaDetail: MangaDetail
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
                model = mangaDetail.main_picture.large,
                contentDescription = "Anime Picture",
                contentScale = ContentScale.FillWidth
            )

            BasicStats(mangaDetail)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            text = mangaDetail.title,
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "by ${mangaDetail.authors.joinToString(separator = ", ") { it.node.last_name + " " +  it.node.first_name }}",
            fontSize = 18.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "EN: ${mangaDetail.alternative_titles.en}",
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "JP: ${mangaDetail.alternative_titles.ja}",
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )




        Text(
            modifier = Modifier.fillMaxWidth(),
            text = mangaDetail.genres.joinToString(separator = ", ") { it.name },
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
            text = mangaDetail.synopsis,
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Spacer(modifier = Modifier.height(8.dp))

        SendElseWhere(text = "Recommendations",
            onClick = {
                navController.navigate(Screen.RecommendationsScreen.withArgs(mangaDetail.id.toString(), "1"))
            })
        Spacer(modifier = Modifier.height(12.dp))
        SendElseWhere(text = "Open in Browser",
            onClick = {
                val uri = "https://myanimelist.net/manga/${mangaDetail.id}/"
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
                contentDescription = "Go to location"
            )
        }
    }
    Divider(modifier = Modifier.height(1.dp), color = Color.White)
}


@Composable
private fun BasicStats(mangaDetail: MangaDetail) {
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
                text = mangaDetail.mean.toString(),
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
                    text = "Rank:\n#${mangaDetail.rank}",
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Popularity:\n#" + "%,d".format(mangaDetail.popularity),
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )



                Text(
                    text = "Members:\n" + "%,d".format(mangaDetail.num_list_users),
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )



                Text(
                    text = "Favorites:\n" + "%,d".format(mangaDetail.num_scoring_users),
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
                        mangaDetail.media_type.replaceFirstChar {
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
                    text = "Chapters:\n${mangaDetail.num_chapters}",
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Volumes: \n${mangaDetail.num_volumes}",
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Started: \n${mangaDetail.created_at}",
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}