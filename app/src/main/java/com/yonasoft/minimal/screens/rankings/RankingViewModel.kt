package com.yonasoft.minimal.screens.rankings

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
class RankingViewModel @Inject constructor(private val repository: Repository): ViewModel() {
    var loading by mutableStateOf(false)
    var rankingList by  mutableStateOf(listOf<AnimeDetail>())
    var currentRankingType by mutableStateOf("All")


    init{
        getRanking()
    }

    fun getRanking(rankingType:String = "All"){
        currentRankingType = rankingType
        viewModelScope.launch {
            getRanking2(rankingType = rankingType)
        }
    }

    private suspend fun getRanking2(rankingType:String = "all") {
        loading = true
        val airingListResponse =
            try {
                repository.getAnimeRanking(
                    rankingType = rankingType.lowercase(Locale.getDefault()),
                    limit = 50,
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
            rankingList = result
            loading = false
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