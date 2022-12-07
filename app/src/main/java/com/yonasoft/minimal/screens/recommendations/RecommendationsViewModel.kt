package com.yonasoft.minimal.screens.recommendations

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yonasoft.minimal.model.anime_detail_model.AnimeDetail
import com.yonasoft.minimal.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class RecommendationsViewModel @Inject constructor(
    private val repository: Repository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var animeId: Int = savedStateHandle["anime_id2"]!!
    var loading by mutableStateOf(true)
    var title by mutableStateOf("")
    var animeDetail: AnimeDetail? = null
    var recommendations = mutableListOf<AnimeDetail>()

    init {
        viewModelScope.launch {
            getRecommendations()
        }
    }

    private suspend fun getRecommendations() {
        val curr = getAnimeDetail(animeId)
        title = curr!!.title
        for (rec in curr.recommendations) {
            var detail: AnimeDetail

            val response =
                try {
                    repository.getAnimeDetails(animeId = rec.node.id)
                } catch (e: IOException) {
                    Log.e("HVM", "IOException")
                    continue
                } catch (e: HttpException) {
                    Log.e("HVM", "HttpException")
                    continue
                }
            if (response.isSuccessful && response.body() != null) {
                detail = response.body()!!
                recommendations.add(detail)
            } else {
                break
            }
        }
        loading = false
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
            response.body()!!
        } else {
            null
        }
    }
}