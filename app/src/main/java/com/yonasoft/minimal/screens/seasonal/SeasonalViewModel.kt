package com.yonasoft.minimal.screens.seasonal

import androidx.lifecycle.ViewModel
import com.yonasoft.minimal.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SeasonalViewModel @Inject constructor(private val repository: Repository):ViewModel() {
}