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

    private var animeId: Int = savedStateHandle["anime_id"]!!
    var loadingDetail by mutableStateOf(true)
    var animeDetail: AnimeDetail? = null

    init {
        getAnimeDetail(animeId = animeId)

    }

    private fun getAnimeDetail(animeId: Int) {
        viewModelScope.launch {
            getAnimeDetailPrivate(animeId = animeId)
        }
    }

    private suspend fun getAnimeDetailPrivate(animeId: Int) {
        val response =
            try {
                repository.getAnimeDetails(animeId = animeId)
            } catch (e: IOException) {
                Log.e("HVM", "IOException")
                return
            } catch (e: HttpException) {
                Log.e("HVM", "HttpException")
                return
            }
        if (response.isSuccessful && response.body() != null) {
            animeDetail = response.body()!!
            loadingDetail = false
        } else {
            return
        }
    }
}

