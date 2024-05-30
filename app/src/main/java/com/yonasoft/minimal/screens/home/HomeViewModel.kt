package com.yonasoft.minimal.screens.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yonasoft.minimal.model.anime_detail_model.AnimeDetail
import com.yonasoft.minimal.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    var airingRanking: List<AnimeDetail> by mutableStateOf(listOf())
    var airRankingLoading: Boolean by mutableStateOf(false)
    private var airRankingOffset = mutableStateOf(0)

    var seasonal: List<AnimeDetail> by mutableStateOf(listOf())
    var seasonalLoading: Boolean by mutableStateOf(false)
    private var seasonalOffset = mutableStateOf(0)

    init {
        viewModelScope.launch(Dispatchers.Main) {
            airRankingLoading = true
            loadAiringRanking()
            airRankingLoading = false

            seasonalLoading = true
            loadSeasonal()
            seasonalLoading = false
        }
    }

    suspend fun loadAiringRanking() {

        val airingListResponse =
            try {
                repository.getAnimeRanking(
                    rankingType = "airing",
                    limit = 4,
                    offset = airRankingOffset.value,
                    fields = ""
                )
            } catch (e: IOException) {
                Log.e("HVM", "IOException")
                airRankingLoading = false
                return
            } catch (e: HttpException) {
                Log.e("HVM", "HttpException")
                airRankingLoading = false
                return
            }
        if (airingListResponse.isSuccessful && airingListResponse.body() != null) {
            val result = mutableListOf<AnimeDetail>()
            for (item in airingListResponse.body()!!.data) {
                getAnimeDetail(item.node.id)?.let { result.add(it) }
            }
            airingRanking += result
            airRankingOffset.value += result.size
        }

    }

    suspend fun loadSeasonal() {

        val season = when (Calendar.getInstance().get(Calendar.MONTH)) {
            0, 1, 2 -> "winter"
            3, 4, 5 -> "spring"
            6, 7, 8 -> "summer"
            9, 10, 11 -> "fall"
            else -> ""
        }

        val response =
            try {
                repository.getAnimeSeasonal(
                    sort = "anime_score",
                    year = Calendar.getInstance().get(Calendar.YEAR),
                    season = season,
                    limit = 4,
                    offset = seasonalOffset.value,
                    fields = ""
                )
            } catch (e: IOException) {
                Log.e("HVM", "IOException")
                seasonalLoading = false
                return
            } catch (e: HttpException) {
                Log.e("HVM", "HttpException")
                seasonalLoading = false
                return
            }

        if (response.isSuccessful && response.body() != null) {
            val result = mutableListOf<AnimeDetail>()
            for (item in response.body()!!.data) {
                getAnimeDetail(item.node.id)?.let { result.add(it) }
            }
            seasonal += result
            seasonalOffset.value += result.size
        }
    }

    private suspend fun getAnimeDetail(animeId: Int): AnimeDetail? {
        val response =
            try {
                repository.getAnimeDetails(animeId = animeId)
            } catch (e: IOException) {
                Log.e("HVM", "IOException")
                return null
            } catch (e: HttpException) {
                Log.e("HVM", "HttpException")
                return null
            }
        return if (response.isSuccessful && response.body() != null) {
            response.body()
        } else {
            null
        }
    }
}
