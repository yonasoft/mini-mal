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
class LoginViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private var token: AccessToken by mutableStateOf(AccessToken())
    private val LOG_TAG = "LoginViewModel"
    private val clientId = MALAuth.clientId
    val state = MALAuth.state
    private val codeVerifier = PkceGenerator.generateVerifier(length = 128)
    val authUrl = "${MALAuth.authUri}authorize?response_type=code&client_id=${clientId}&code_challenge=${codeVerifier}&state=${state}"

    fun handleAuthorizationCode(code: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = fetchAccessToken(code)
            handleResponse(response)
        }
    }

    private suspend fun fetchAccessToken(code: String): Response<AccessToken>? {
        Log.d(LOG_TAG, "start of function")
        return try {
            repository.retrieveAccessToken(clientId = clientId, code = code, codeVerifier = codeVerifier)
        } catch (e: IOException) {
            Log.d(LOG_TAG, "Exception", e)
            null
        }
    }

    private fun handleResponse(response: Response<AccessToken>?) {
        Log.d(LOG_TAG, "response: $response")
        response?.let {
            if (it.isSuccessful && it.body() != null) {
                token = it.body()!!
            }
        }
        Log.d(LOG_TAG, "access token is ${token.access_token}")
    }

}