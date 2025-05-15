package com.jluqgon214.hogarmate.repository

import com.jluqgon214.hogarmate.client.RetrofitClient
import com.jluqgon214.hogarmate.model.LoginRequest
import com.jluqgon214.hogarmate.model.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * # Repositorio encargado de gestionar las operaciones relacionadas con el inicio de sesión.
 */
class LoginRepository {
    /**
     * ### Realiza una solicitud de inicio de sesión a la API.
     *
     * @param username Nombre de usuario proporcionado para el inicio de sesión.
     * @param password Contraseña proporcionada para el inicio de sesión.
     * @return Una respuesta de tipo `Response<LoginResponse>` que contiene los datos de la respuesta de la API.
     */
    suspend fun login(username: String, password: String): Response<LoginResponse> {
        // Crea un objeto de solicitud de inicio de sesión con el nombre de usuario y la contraseña.
        val loginRequest = LoginRequest(username, password)

        // Realiza la llamada a la API en un contexto de IO para operaciones de red.
        return withContext(Dispatchers.IO) {
            RetrofitClient.instance.login(loginRequest).execute()
        }
    }
}