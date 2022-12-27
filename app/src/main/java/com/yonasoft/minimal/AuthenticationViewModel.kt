package com.yonasoft.minimal

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yonasoft.minimal.model.token.AccessToken
import com.yonasoft.minimal.network.MALAuth
import com.yonasoft.minimal.repository.Repository
import com.yonasoft.minimal.util.PkceGenerator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val repository: Repository,
    auth: MALAuth
) :
    ViewModel() {

    var token: AccessToken by mutableStateOf(AccessToken())

    private val clientId = auth.clientId
    val state = auth.state
    private val redirect = auth.redirectUri
    private val codeVerifier = PkceGenerator.generateVerifier(length = 128)
    val authUrl = "${auth.authUri}authorize?response_type=code&client_id=${clientId}&code_challenge=${codeVerifier}&state=${state}&redirect_uri=${redirect}"

    fun handleAuthorizationCode(code: String) {
        viewModelScope.launch {
            val response =
                try {
                    Log.d("auth", "code is $code")
                    repository.retrieveAccessToken(code = code, codeVerifier = codeVerifier)
                } catch (e: Exception) {
                    Log.e("HVM", "Exception")
                    null
                }
            Log.d("auth", "response is $response")
            token = response!!.body()!!
            Log.d("auth", "token is ${token.accessToken}")

        }
    }

    fun getMyList(
        token: String ,
        status: String = "watching",
        sort: String = "anime_title",
        limit: Int = 20,
        offset: Int = 0
    ) {
        viewModelScope.launch {
            val list = repository.getMyAnimeList(
                token = token, status = status, sort = sort, limit = limit, offset = offset
            ).body()
            if (list != null) {
                Log.d("auth5", "list:" + list.data.toString())
            }
        }
    }
}