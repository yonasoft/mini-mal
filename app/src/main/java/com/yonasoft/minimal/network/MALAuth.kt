package com.yonasoft.minimal.network

import javax.inject.Singleton

//File to provide MAL authentication information utilities
@Singleton
class MALAuth {
        companion object {
                const val responseType = "code"
                const val clientId = "512280769d924c98520164d8d05b0954"
                const val state = "Requestmmal1029"
                const val codeChallengeMethod = "plain"
                const val grantType = "authorization_code"
                const val redirectUri = "auth://callback"

                //Authentication Uri
                const val authUri = "https://myanimelist.net/v1/oauth2/"

                //Token Uri
                const val tokenUri = "https://myanimelist.net/v1/oauth2/"

                const val contentType = "application/x-www-form-urlencoded"
        }

}