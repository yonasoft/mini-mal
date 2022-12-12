package com.yonasoft.minimal.screens.search

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
class SearchViewModel @Inject constructor(
    private var repository: Repository,
) : ViewModel() {
    var searchQuery by mutableStateOf("")

    var animeResult by mutableStateOf(listOf<AnimeDetail>())
    var mangaResult by mutableStateOf(listOf<MangaDetail>())

    var animeLoading by mutableStateOf(false)
    var mangaLoading by mutableStateOf(false)


    fun getAnimeList(query: String = searchQuery) {
        if(query.isNotEmpty()) {
            viewModelScope.launch {
                getAnimeListPrivate(query = query)
            }
        }
    }

    fun getMangaList(query: String = searchQuery) {
        if(query.isNotEmpty()) {
            viewModelScope.launch {
                getMangaListPrivate(query = query)
            }
        }
    }

    private suspend fun getAnimeListPrivate(query: String) {
        animeLoading = true
        val searchResponse =
            try {
                repository.getAnimeList(
                    query = query,
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
        if (searchResponse.isSuccessful && searchResponse.body() != null) {
            val result = mutableListOf<AnimeDetail>()
            for (item in searchResponse.body()!!.data) {
                getAnimeDetail(item.node.id)?.let { result.add(it) }
            }
            animeResult = result
            animeLoading = false
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


    private suspend fun getMangaListPrivate(query: String) {
        mangaLoading = true
        val searchResponse =
            try {
                repository.getMangaList(
                    query = query,
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
        if (searchResponse.isSuccessful && searchResponse.body() != null) {
            Log.d("manga list","${searchResponse.body()}")
            val result = mutableListOf<MangaDetail>()
            for (item in searchResponse.body()!!.data) {
                getMangaDetail(item.node.id)?.let { result.add(it) }
            }
            mangaResult = result
            mangaLoading = false
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
        Log.d("manga list","${response.body()}")
        return if (response.isSuccessful && response.body() != null) {
            response.body()
        } else {
            null
        }
    }
}