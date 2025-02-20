package com.jluqgon214.hogarmate.repository

import com.jluqgon214.hogarmate.client.RetrofitClient
import com.jluqgon214.hogarmate.model.RegisterRequest
import com.jluqgon214.hogarmate.model.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class RegisterRepository {
    suspend fun register(
        username: String,
        email: String,
        password: String,
        passwordRepeat: String,
        rol: String = "USER",
        hogar: String? = null
    ): Response<RegisterResponse> {
        val registerRequest = RegisterRequest(username, email, password, passwordRepeat, rol, hogar)
        return withContext(Dispatchers.IO) {
            RetrofitClient.instance.register(registerRequest).execute()
        }
    }
}