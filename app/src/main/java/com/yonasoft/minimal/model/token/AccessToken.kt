package com.yonasoft.minimal.model.token

data class AccessToken(
    val access_token: String?=null,
    val expires_in: Int?=null,
    val refresh_token: String?=null,
    val token_type: String?=null
)