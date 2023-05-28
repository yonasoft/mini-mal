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
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository,
) :
    ViewModel() {

    private var token: AccessToken by mutableStateOf(AccessToken())

    private val clientId = MALAuth.clientId
    val state = MALAuth.state
    private val codeVerifier = PkceGenerator.generateVerifier(length = 128)
    val authUrl =
        "${MALAuth.authUri}authorize?response_type=code&client_id=${clientId}&code_challenge=${codeVerifier}&state=${state}"

    fun handleAuthorizationCode(code: String) {
        viewModelScope.launch(Dispatchers.IO) {
            handleAuthorizationCode2(code)
        }
    }

    private suspend fun handleAuthorizationCode2(code: String) {
        Log.d("auth", "start of function")
        Log.d("auth", "code parameter: $code")
        Log.d("auth", "access token before response: ${token.access_token}")
        var response:Response<AccessToken>?=null
            try {
                response = repository.retrieveAccessToken(
                    clientId = clientId,
                    code = code,
                    codeVerifier = codeVerifier)
                Log.d("auth","After Api call")
            } catch (e: IOException) {
                Log.d("auth", "Exception")
                null
            }
        Log.d("auth", "response: $response")
        if (response!!.isSuccessful && response.body() != null) {
            token = response.body()!!
        }
        Log.d("auth", "access token is ${token.access_token}")
        Log.d("auth", "end of function")
    }

    fun getMyList(
        token: String,
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