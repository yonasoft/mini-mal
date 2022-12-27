package com.yonasoft.minimal.network

import com.yonasoft.minimal.model.token.AccessToken
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface MALApi2 {

    @POST("token")
    suspend fun getAccessToken(
        @Query("client_id") clientId: String,
        @Query("code") code: String,
        @Query("grant_type") grantType: String,
        @Query("code_verifier") codeVerifier: String,
    ): Response<AccessToken>

    @POST("token")
    suspend fun getAccessToken(
        @Query("client_id") clientId: String,
        @Query("refresh_token") refreshToken:String,
        @Query("code_verifier") codeVerifier: String,
    ): Response<AccessToken>

}