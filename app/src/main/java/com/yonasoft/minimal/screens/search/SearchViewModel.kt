package com.yonasoft.minimal.screens.search

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
class SearchViewModel @Inject constructor( private var repository: Repository, state: SavedStateHandle) : ViewModel() {
    var searchQuery by mutableStateOf("")

    var searchResult by mutableStateOf(listOf<AnimeDetail>())
    var searchLoading by mutableStateOf(false)


    init {
        //Passing an empty string as an arg when navigating leads to an error.
        //Multiple alternatives and work-arounds were attempted but what you see below is best option out of them.
        //A string with just a space is passed and then SearchViewModel will initial based on if the arg is " " or something else(User input)
         if (state.get<String>("initial_search")!=" ") {
             searchQuery = state.get<String>("initial_search").toString()
         }
        getAnimeList(searchQuery)
    }

    fun getAnimeList(query:String){
        viewModelScope.launch {
            getAnimeListPrivate(query = query)
        }
    }

    private suspend fun getAnimeListPrivate(query:String) {
        searchLoading = true
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
            for(item in searchResponse.body()!!.data){
                getAnimeDetail(item.node.id)?.let { result.add(it) }
            }
            searchResult = result
            searchLoading = false
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