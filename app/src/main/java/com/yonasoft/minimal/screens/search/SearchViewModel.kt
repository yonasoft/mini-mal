package com.yonasoft.minimal.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(state: SavedStateHandle) : ViewModel() {
    var searchQuery by mutableStateOf("")

    init {
        //Passing an empty string as an arg when navigating leads to an error.
        //Multiple alternatives and work-arounds were attempted but what you see below is best option out of them.
        //A string with just a space is passed and then SearchViewModel will initial based on if the arg is " " or something else(User input)

         if (state.get<String>("initial_search")!=" ") {
             searchQuery = state.get<String>("initial_search").toString()
         }
    }

}