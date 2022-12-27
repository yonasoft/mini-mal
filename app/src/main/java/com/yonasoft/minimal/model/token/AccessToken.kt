package com.yonasoft.minimal.model.token

data class AccessToken(

    val tokenType: String = "",
    val expiresIn: Int = 0,
    val accessToken: String? = null,
    val refreshToken: String? = null,
    val error: String? = null,
    val message: String? = null,
)