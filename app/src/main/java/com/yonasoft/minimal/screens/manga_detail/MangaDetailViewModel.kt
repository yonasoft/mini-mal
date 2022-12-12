package com.yonasoft.minimal.screens.manga_detail


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yonasoft.minimal.model.manga_detail_model.MangaDetail
import com.yonasoft.minimal.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class MangaDetailViewModel @Inject constructor(
    private val repository: Repository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var mangaId: Int = savedStateHandle["manga_id"]!!
    var loadingDetail by mutableStateOf(true)
    var mangaDetail: MangaDetail? = null

    init {
        getMangaDetail(mangaId=mangaId)
    }

    private fun getMangaDetail(mangaId: Int) {
        viewModelScope.launch {
            getMangaDetailPrivate(mangaId =mangaId)
        }
    }

    private suspend fun getMangaDetailPrivate(mangaId: Int) {
        loadingDetail = true
        val response =
            try {
                repository.getMangaDetails(mangaId = mangaId)
            } catch (e: IOException) {
                Log.e("HVM", "IOException")
                return
            } catch (e: HttpException) {
                Log.e("HVM", "HttpException")
                return
            }
        if (response.isSuccessful && response.body() != null) {
            mangaDetail = response.body()!!
            loadingDetail = false
        } else {
            return
        }
    }
}

