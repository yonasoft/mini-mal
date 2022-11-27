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
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    var airingRanking: List<AnimeDetail> by mutableStateOf(listOf())
    var airRankingLoading: Boolean by mutableStateOf(false)

    var seasonal: List<AnimeDetail> by mutableStateOf(listOf())
    var seasonalLoading: Boolean by mutableStateOf(false)

    init {
        viewModelScope.launch {
            initializeAiringRanking()
            initializeSeasonal()
        }
    }

    private suspend fun initializeAiringRanking() {
        airRankingLoading = true
        val airingListResponse =
            try {
                repository.getAnimeRanking(
                    rankingType = "airing",
                    limit = 20,
                    offset = 0,
                    fields = ""
                )
            } catch (e: IOException) {
                Log.e("HVM", "IOException")
                return
            } catch (e: HttpException) {
                Log.e("HVM", "HttpException")
                return
            }
        if (airingListResponse.isSuccessful && airingListResponse.body() != null) {
            val result = mutableListOf<AnimeDetail>()
            for(item in airingListResponse.body()!!.data){
                getAnimeDetail(item.node.id)?.let { result.add(it) }
            }
            airingRanking = result
            airRankingLoading = false
        }

    }

    private suspend fun initializeSeasonal() {
        seasonalLoading = true
        val season = when (Calendar.getInstance().get(Calendar.MONTH)) {
            0 -> "winter"
            1 -> "winter"
            2 -> "winter"
            3 -> "spring"
            4 -> "spring"
            5 -> "spring"
            6 -> "summer"
            7 -> "summer"
            8 -> "summer"
            9 -> "fall"
            10 -> "fall"
            11 -> "fall"

            else -> {""}
        }
        val response =
            try {
                repository.getAnimeSeasonal(
                    sort = "anime_score",
                    year = Calendar.getInstance().get(Calendar.YEAR),
                    season = season,
                    limit = 20,
                    offset = 0,
                    fields = ""
                )
            } catch (e: IOException) {
                Log.e("HVM", "IOException")
                return
            } catch (e: HttpException) {
                Log.e("HVM", "HttpException")
                return
            }

        if (response.isSuccessful && response.body() != null) {
            val result = mutableListOf<AnimeDetail>()
            for(item in response.body()!!.data){
                getAnimeDetail(item.node.id)?.let { result.add(it) }
            }
            seasonal = result
            seasonalLoading = false
        }
    }

    private suspend fun getAnimeDetail(animeId:Int): AnimeDetail? {
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
        } else{
            null
        }
    }
}