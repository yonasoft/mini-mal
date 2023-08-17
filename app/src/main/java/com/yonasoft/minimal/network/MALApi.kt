package com.yonasoft.minimal.network

import com.yonasoft.minimal.model.anime_detail_model.AnimeDetail
import com.yonasoft.minimal.model.anime_model.Anime
import com.yonasoft.minimal.model.manga_detail_model.MangaDetail
import com.yonasoft.minimal.model.manga_model.Manga
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

//Api to access MAL's data
interface MALApi {
    @GET("v2/anime")
    suspend fun getAnimeList(
        @Header("X-MAL-CLIENT-ID") clientId: String,
        @Query("q") query:String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("fields") fields: String
    ): Response<Anime>

    @GET("v2/anime/ranking")
    suspend fun getAnimeRanking(
        @Header("X-MAL-CLIENT-ID") clientId: String,
        @Query("ranking_type") rankingType:String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("fields") fields: String
    ): Response<Anime>

    @GET("v2/anime/season/{year}/{season}")
    suspend fun getAnimeSeasonal(
        @Header("X-MAL-CLIENT-ID") clientId: String,
        @Path("year") year: Int,
        @Path("season") season: String,
        @Query("sort") sort:String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("fields") fields: String
        ): Response<Anime>

    @GET("v2/anime/suggestions")
    suspend fun getAnimeSuggested(
        @Header("X-MAL-CLIENT-ID") clientId: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("fields") fields: String
    ): Response<Anime>

    @GET("v2/anime/{anime_id}")
    suspend fun getAnimeDetails(
        @Header("X-MAL-CLIENT-ID") clientId: String,
        @Path("anime_id") animeId:Int,
        @Query("fields") fields: String
    ): Response<AnimeDetail>

    @GET("v2/manga")
    suspend fun getMangaList(
        @Header("X-MAL-CLIENT-ID") clientId: String,
        @Query("q") query:String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("fields") fields: String
    ): Response<Manga>

    @GET("v2/manga/{manga_id}")
    suspend fun getMangaDetails(
        @Header("X-MAL-CLIENT-ID") clientId: String,
        @Path("manga_id") mangaId: Int,
        @Query("fields") fields: String
    ): Response<MangaDetail>


    @GET("v2/users/@me/animelist")
    suspend fun getMyAnimeList(
        @Header("Authorization") token:String,
        @Query("status") status:String,
        @Query("sort") sort:String,
        @Query("limit") limit:Int,
        @Query("offset") offset:Int
    ):Response<Anime>

}