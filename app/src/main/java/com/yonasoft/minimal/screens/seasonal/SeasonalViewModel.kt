package com.yonasoft.minimal.screens.seasonal

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
class SeasonalViewModel @Inject constructor(private val repository: Repository):ViewModel() {


    var loading by mutableStateOf(true)
    var season by mutableStateOf(when (Calendar.getInstance().get(Calendar.MONTH)) {
        0,1,2 -> "winter"
        3,4,5 -> "spring"
        6,7,8 -> "summer"
        9,10,11 -> "fall"
        else -> ""
    })
    var year by mutableStateOf(Calendar.getInstance().get(Calendar.YEAR))
    var seasonalList by mutableStateOf(listOf<AnimeDetail>())

    init {
        getSeasonal()
    }

    fun getSeasonal(season:String= this.season, year: Int=this.year){
        viewModelScope.launch {
            getSeasonal2(season = season, year = year)
        }
    }

    private suspend fun getSeasonal2(season:String = this.season, year: Int = this.year) {
        loading = true

        val response =
            try {
                repository.getAnimeSeasonal(
                    sort = "anime_score",
                    year = year,
                    season = season,
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

        if (response.isSuccessful && response.body() != null) {
            val result = mutableListOf<AnimeDetail>()
            for(item in response.body()!!.data){
                getAnimeDetail(item.node.id)?.let { result.add(it) }
            }
            seasonalList = result
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