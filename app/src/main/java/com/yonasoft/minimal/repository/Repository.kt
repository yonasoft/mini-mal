package com.yonasoft.minimal.repository

import android.util.Log
import com.yonasoft.minimal.model.anime_detail_model.AnimeDetail
import com.yonasoft.minimal.model.anime_model.Anime
import com.yonasoft.minimal.model.manga_detail_model.MangaDetail
import com.yonasoft.minimal.model.manga_model.Manga
import com.yonasoft.minimal.model.token.AccessToken
import com.yonasoft.minimal.network.MALAuth
import com.yonasoft.minimal.network.RetrofitInstance
import retrofit2.Response
import javax.inject.Singleton

@Singleton
class Repository {
    suspend fun getAnimeList(
        query: String,
        limit: Int,
        offset: Int,
        fields: String
    ): Response<Anime> {
        return RetrofitInstance.api.getAnimeList(
            clientId = MALAuth.clientId,
            query = query,
            limit = limit,
            offset = offset,
            fields = fields
        )
    }

    suspend fun getAnimeRanking(
        rankingType: String,
        limit: Int,
        offset: Int,
        fields: String
    ): Response<Anime> {
        return RetrofitInstance.api.getAnimeRanking(
            clientId = MALAuth.clientId,
            rankingType = rankingType,
            limit = limit,
            offset = offset,
            fields = fields
        )
    }
    suspend fun getAnimeSeasonal(
        year: Int,
        season: String,
        sort: String,
        limit: Int,
        offset: Int,
        fields: String
    ): Response<Anime> {
        return RetrofitInstance.api.getAnimeSeasonal(
            clientId = MALAuth.clientId,
            year = year,
            season = season,
            sort = sort,
            limit = limit,
            offset = offset,
            fields = ""
        )
    }
    suspend fun getAnimeSuggested(
        limit: Int,
        offset: Int,
        fields: String = ""
    ): Response<Anime> {
        return RetrofitInstance.api.getAnimeSuggested(
            clientId = MALAuth.clientId,
            limit = limit,
            offset = offset,
            fields = fields
        )
    }

    suspend fun getAnimeDetails(
        clientId: String = MALAuth.clientId,
        animeId: Int,
    ): Response<AnimeDetail> {
        return RetrofitInstance.api.getAnimeDetails(
            clientId = clientId,
            animeId = animeId,
            fields = "id,title,main_picture,alternative_titles,start_date,end_date,synopsis,mean,rank,popularity,num_list_users,num_scoring_users,nsfw,created_at,updated_at,media_type,status,genres,my_list_status,num_episodes,start_season,broadcast,source,average_episode_duration,rating,pictures,background,related_anime,related_manga,recommendations,studios,statistics"
        )
    }

    suspend fun getMangaList(
        query: String,
        limit: Int,
        offset: Int,
        fields: String
    ): Response<Manga> {
        return RetrofitInstance.api.getMangaList(
            clientId = MALAuth.clientId,
            query = query,
            limit = limit,
            offset = offset,
            fields = fields
        )
    }

    suspend fun getMangaDetails(
        clientId: String = MALAuth.clientId,
        mangaId: Int,
    ): Response<MangaDetail> {
        return RetrofitInstance.api.getMangaDetails(
            clientId = clientId,
            mangaId = mangaId,
            fields = "id,title,main_picture,alternative_titles,start_date,end_date,synopsis,mean,rank,popularity,num_list_users,num_scoring_users,nsfw,created_at,updated_at,media_type,status,genres,my_list_status,num_volumes,num_chapters,authors{first_name,last_name},pictures,background,related_anime,related_manga,recommendations,serialization"
        )
    }

    suspend fun retrieveAccessToken(
        clientId: String,
        code: String,
        codeVerifier: String,
        grantType: String = MALAuth.grantType,
    ): Response<AccessToken> {
        Log.d("rep", "retrieve token is running")
        Log.d("rep", "code is $code")
        Log.d("rep", "verify is $codeVerifier")
        return RetrofitInstance.api2.getAccessToken(
            clientId = clientId,
            grantType = grantType,
            code = code,
            codeVerifier = codeVerifier,
        )
    }

    suspend fun getMyAnimeList(
        token: String,
        status: String,
        sort: String,
        limit: Int,
        offset: Int
    ): Response<Anime> {
        return RetrofitInstance.api.getMyAnimeList(
            token = "Bearer $token",
            status = status,
            sort = sort,
            limit = limit,
            offset = offset
        )

    }
}