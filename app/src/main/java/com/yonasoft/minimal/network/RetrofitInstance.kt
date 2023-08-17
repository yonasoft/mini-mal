package com.yonasoft.minimal.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
object RetrofitInstance {
    val api: MALApi =
        Retrofit.Builder()
            .baseUrl("https://api.myanimelist.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MALApi::class.java)

    val api2: MALApi2 =
        Retrofit.Builder()
            .baseUrl("https://myanimelist.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MALApi2::class.java)
}