package com.jluqgon214.hogarmate.service

import com.jluqgon214.hogarmate.model.LoginRequest
import com.jluqgon214.hogarmate.model.LoginResponse
import com.jluqgon214.hogarmate.model.RegisterRequest
import com.jluqgon214.hogarmate.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/usuarios/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("/usuarios/register")
    fun register(@Body request: RegisterRequest): Call<RegisterResponse>
}                               