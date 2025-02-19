package com.jluqgon214.hogarmate.service

import com.jluqgon214.hogarmate.model.LoginRequest
import com.jluqgon214.hogarmate.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}