package com.yonasoft.minimal.model.anime_detail_model

data class RelatedAnime(
    val node: Node,
    val relation_type: String,
    val relation_type_formatted: String
)