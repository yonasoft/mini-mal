package com.yonasoft.minimal.screens.shared

import androidx.lifecycle.ViewModel
import com.yonasoft.minimal.repository.Repository
import javax.inject.Inject


class SharedViewModel @Inject constructor(private val repository: Repository) :ViewModel()