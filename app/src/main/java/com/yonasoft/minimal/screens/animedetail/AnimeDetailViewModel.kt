package com.yonasoft.minimal.screens.animedetail

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
class AnimeDetailViewModel @Inject constructor(
    private val repository: Repository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val animeId: Int = savedStateHandle["anime_id"] ?: 0
    var loadingDetail by mutableStateOf(true)
    var animeDetail: AnimeDetail? = null

    init {
        fetchAnimeDetail()
    }

    private fun fetchAnimeDetail() {
        viewModelScope.launch {
            try {
                val response = repository.getAnimeDetails(animeId = animeId)
                if (response.isSuccessful) {
                    animeDetail = response.body()
                    loadingDetail = false
                }
            } catch (e: IOException) {
                Log.e("AnimeDetailViewModel", "IOException", e)
            } catch (e: HttpException) {
                Log.e("AnimeDetailViewModel", "HttpException", e)
            }
        }
    }
}
