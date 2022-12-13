package com.yonasoft.minimal.screens.recommendations

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yonasoft.minimal.model.anime_detail_model.AnimeDetail
import com.yonasoft.minimal.model.manga_detail_model.MangaDetail
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

    private var recommendationId: Int = savedStateHandle["rec_id"]!!
    var recommendationType:Int = savedStateHandle["rec_type"]!!

    var loading by mutableStateOf(true)

    var title by mutableStateOf("")

    var animeRecommendations = mutableListOf<AnimeDetail>()
    var mangaRecommendations = mutableListOf<MangaDetail>()

    var mangaDetail: MangaDetail? = null

    init {
        viewModelScope.launch {
            when(recommendationType){
                0 -> getAnimeRecommendations()
                1 -> getMangaRecommendations()
            }
        }
    }

    private suspend fun getAnimeRecommendations() {
        loading = true
        val curr = getAnimeDetail(recommendationId)
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
                animeRecommendations.add(detail)
            } else {
                break
            }
        }
        loading = false
    }

    private suspend fun getMangaRecommendations() {
        loading = true
        val curr = getMangaDetail(recommendationId)
        title = curr!!.title
        for (rec in curr.recommendations) {
            var detail: MangaDetail

            val response =
                try {
                    repository.getMangaDetails(mangaId = rec.node.id)
                } catch (e: IOException) {
                    Log.e("HVM", "IOException")
                    continue
                } catch (e: HttpException) {
                    Log.e("HVM", "HttpException")
                    continue
                }
            if (response.isSuccessful && response.body() != null) {
                detail = response.body()!!
                mangaRecommendations.add(detail)
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

    private suspend fun getMangaDetail(mangaId: Int): MangaDetail? {
        val response =
            try {
                repository.getMangaDetails(mangaId = mangaId)
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