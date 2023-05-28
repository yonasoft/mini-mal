package com.yonasoft.minimal.network

import javax.inject.Singleton

//File to provide MAL authentication information utilities
@Singleton
class MALAuth {
        companion object {
                val responseType = "code"
                val clientId = "512280769d924c98520164d8d05b0954"
                val state = "Requestmmal1029"
                val codeChallengeMethod = "plain"
                val grantType = "authorization_code"
                val redirectUri = "auth://callback"

                //Authentication Uri
                val authUri = "https://myanimelist.net/v1/oauth2/"

                //Token Uri
                val tokenUri = "https://myanimelist.net/v1/oauth2/"

                val contentType = "application/x-www-form-urlencoded"
        }

}