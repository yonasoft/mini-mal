package com.yonasoft.minimal.screens.rankings

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yonasoft.minimal.model.anime_detail_model.AnimeDetail
import com.yonasoft.minimal.model.anime_model.Anime
import com.yonasoft.minimal.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RankingViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    var loading by mutableStateOf(false)
    var rankingList by mutableStateOf(listOf<AnimeDetail>())
    var currentRankingType by mutableStateOf("")
    private var offset = mutableStateOf(0)


    init {
        getRanking()
    }


    fun getRanking(rankingType: String = currentRankingType) {
        viewModelScope.launch {
            if(rankingType!=currentRankingType) {
                offset.value = 0
            }
            if (offset.value == 0){
                loading = true
            }
            val airingListResponse:Response<Anime> =
                try {
                    repository.getAnimeRanking(
                        rankingType = rankingType.lowercase(Locale.getDefault()),
                        limit = 7,
                        offset = offset.value,
                        fields = ""
                    )
                } catch (e: IOException) {
                    Log.e("HVM", "IOException")
                    loading = false
                    return@launch
                } catch (e: HttpException) {
                    Log.e("HVM", "HttpException")
                    loading = false
                    return@launch
                }

            if (airingListResponse.isSuccessful && airingListResponse.body() != null) {
                val result = mutableListOf<AnimeDetail>()
                for (item in airingListResponse.body()!!.data) {
                    getAnimeDetail(item.node.id)?.let { result.add(it) }
                }
                rankingList += result
                offset.value += result.size
            }
            loading = false
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