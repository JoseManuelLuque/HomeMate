package com.jluqgon214.hogarmate.client

import com.jluqgon214.hogarmate.service.ApiService
import com.jluqgon214.hogarmate.viewModel.LoginViewModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://app-adat-foah.onrender.com"

    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor { LoginViewModel.getJWT() })
        .build()

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
}