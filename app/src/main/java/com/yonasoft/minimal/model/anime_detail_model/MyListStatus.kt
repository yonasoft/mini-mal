package com.yonasoft.minimal.model.anime_detail_model

data class MyListStatus(
    val is_rewatching: Boolean,
    val num_episodes_watched: Int,
    val score: Int,
    val status: String,
    val updated_at: String
)