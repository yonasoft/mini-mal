package com.yonasoft.minimal.model.manga_detail_model

data class MyListStatus(
    val is_rereading: Boolean,
    val num_chapters_read: Int,
    val num_volumes_read: Int,
    val score: Int,
    val status: String,
    val updated_at: String
)