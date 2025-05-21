package com.jluqgon214.hogarmate.repository

import com.jluqgon214.hogarmate.client.RetrofitClient
import com.jluqgon214.hogarmate.model.RegisterRequest
import com.jluqgon214.hogarmate.model.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * # Repositorio encargado de manejar el registro de usuarios en la aplicación.
 */
class RegisterRepository {

    /**
     * ### Realiza el registro de un nuevo usuario en el sistema.
     */
    suspend fun register(
        username: String,
        email: String,
        password: String,
        passwordRepeat: String,
        rol: String = "USER",
        hogar: String? = null
    ): Response<RegisterResponse> {
        // Crea una solicitud de registro con los datos proporcionados.
        val registerRequest = RegisterRequest(username, email, password, passwordRepeat, rol, hogar)

        // Ejecuta la solicitud de registro en un contexto de IO.
        return withContext(Dispatchers.IO) {
            RetrofitClient.instance.register(registerRequest).execute()
        }
    }
}