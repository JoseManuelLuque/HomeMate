package com.jluqgon214.hogarmate.client

import com.jluqgon214.hogarmate.service.ApiService
import com.jluqgon214.hogarmate.viewModel.LoginViewModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * # Objeto que proporciona una instancia de Retrofit configurada para interactuar con la API.
 *
 * Este cliente utiliza un interceptor de autenticación para incluir el token JWT en las solicitudes
 * y configura tiempos de espera personalizados para las conexiones, lecturas y escrituras.
 */
object RetrofitClient {
    // URL base de la API.
    private const val BASE_URL = "https://app-adat-foah.onrender.com"

    // Cliente HTTP configurado con un interceptor de autenticación y tiempos de espera.
    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor { LoginViewModel.getJWT() }) // Interceptor para añadir el token JWT.
        .connectTimeout(120, TimeUnit.SECONDS) // Tiempo máximo para establecer la conexión.
        .readTimeout(120, TimeUnit.SECONDS)   // Tiempo máximo para leer datos de la conexión.
        .writeTimeout(120, TimeUnit.SECONDS)  // Tiempo máximo para escribir datos en la conexión.
        .build()

    /**
     * Instancia única de `ApiService` creada de forma perezosa.
     *
     * Esta instancia se utiliza para realizar llamadas a los endpoints de la API.
     */
    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL) // Establece la URL base de la API.
            .client(client) // Asocia el cliente HTTP configurado.
            .addConverterFactory(GsonConverterFactory.create()) // Conversor para manejar JSON.
            .build()

        retrofit.create(ApiService::class.java) // Crea la implementación de la interfaz `ApiService`.
    }
}