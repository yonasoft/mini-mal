package com.yonasoft.minimal.model.manga_detail_model

data class AlternativeTitles(
    val en: String,
    val ja: String,
    val synonyms: List<String>
)