package com.jluqgon214.hogarmate.repository

import com.jluqgon214.hogarmate.client.RetrofitClient
import com.jluqgon214.hogarmate.model.LoginRequest
import com.jluqgon214.hogarmate.model.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class LoginRepository {
    suspend fun login(username: String, password: String): Response<LoginResponse> {
        val loginRequest = LoginRequest(username, password)
        return withContext(Dispatchers.IO) {
            RetrofitClient.instance.login(loginRequest).execute()
        }
    }
}