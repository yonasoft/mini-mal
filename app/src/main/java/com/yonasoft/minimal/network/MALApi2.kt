package com.yonasoft.minimal.network

import com.yonasoft.minimal.model.token.AccessToken
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface MALApi2 {

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("token/v1/oauth2")
    @FormUrlEncoded
    suspend fun getAccessToken(
        @Field("client_id") clientId:String,
        @Field("code") code: String,
        @Field("grant_type") grantType: String,
        @Field("code_verifier") codeVerifier: String,
    ): Response<AccessToken>


}