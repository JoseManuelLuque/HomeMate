package com.jluqgon214.hogarmate.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jluqgon214.hogarmate.model.RegisterResponse
import com.jluqgon214.hogarmate.repository.RegisterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.net.SocketTimeoutException

/**
 * # ViewModel para gestionar el registro de usuarios.
 * Proporciona flujos de estado para los datos del formulario de registro y el manejo de respuestas y errores.
 */
class RegisterViewModel : ViewModel() {
    /**
     * Repositorio utilizado para realizar las operaciones de registro de usuarios.
     */
    private val repository = RegisterRepository()

    /**
     * Flujo de estado que expone el nombre de usuario.
     */
    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    /** ### Actualiza el nombre de usuario.
     * @param username El nuevo nombre de usuario.
     */
    fun setUsername(username: String) {
        _username.value = username
    }

    /**
     * Flujo de estado que expone el correo electrónico.
     */
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    /**
     * ### Actualiza el correo electrónico.
     * @param email El nuevo correo electrónico.
     */
    fun setEmail(email: String) {
        _email.value = email
    }

    /**
     * Flujo de estado que expone la contraseña.
     */
    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    /**
     * ### Actualiza la contraseña.
     * @param password La nueva contraseña.
     */
    fun setPassword(password: String) {
        _password.value = password
    }

    /**
     * Flujo de estado que expone la repetición de la contraseña.
     */
    private val _passwordRepeat = MutableStateFlow("")
    val passwordRepeat: StateFlow<String> = _passwordRepeat

    /**
     * ### Actualiza la repetición de la contraseña.
     * @param password La nueva repetición de la contraseña.
     */
    fun setRepeatPassword(password: String) {
        _passwordRepeat.value = password
    }

    /**
     * Flujo de estado que expone la respuesta del registro.
     */
    private val _registerResponse = MutableStateFlow<RegisterResponse?>(null)
    val registerResponse: StateFlow<RegisterResponse?> = _registerResponse

    /**
     * Flujo de estado que expone el mensaje de error.
     */
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    /**
     * ### Realiza la operación de registro utilizando los datos proporcionados.
     * Actualiza los flujos de estado con la respuesta o el mensaje de error correspondiente.
     */
    fun register() {
        viewModelScope.launch {
            try {
                // Llamada a la API para registrar al usuario
                val response: Response<RegisterResponse> = repository.register(
                    _username.value,
                    _email.value,
                    _password.value,
                    _passwordRepeat.value
                )

                // Si la respuesta es exitosa, actualiza el flujo de estado con la respuesta
                if (response.isSuccessful) {
                    _registerResponse.value = response.body()
                } else {
                    // Obtener el string del error y parsearlo
                    val errorJson = response.errorBody()?.string()

                    // Log para verificar el error
                    Log.d("RegisterViewModel", "Error: $errorJson")

                    // Manejar el error de la respuesta (Parsear el JSON recivido para poder mostrar información al usuario)
                    errorJson?.let {
                        try {
                            val jsonObject = JSONObject(it)
                            // Extraer el mensaje de error del JSON
                            val errorMessage = jsonObject.getString("message")
                            // Actualizar el flujo de estado con el mensaje de error
                            _errorMessage.value = errorMessage
                        } catch (e: JSONException) {
                            _errorMessage.value = it
                            Log.e("RegisterViewModel", "Error al parsear JSON: $e")
                        }
                    } ?: run {
                        // Si no se puede parsear el JSON, asignar un mensaje de error genérico
                        _errorMessage.value = "Error desconocido"

                        // Log para verificar el error
                        Log.e("RegisterViewModel", "Error desconocido")
                    }
                }
            } catch (e: SocketTimeoutException) {
                // Manejar excepciones de tiempo de espera
                _errorMessage.value = "Comprueba tu conexión a Internet"

                // Log para verificar la excepción
                Log.e("RegisterViewModel", "Error de conexión: $e")
            } catch (e: Exception) {
                // Manejar otras excepciones
                _errorMessage.value = "Error: ${e.message}"

                // Log para verificar la excepción
                Log.e("RegisterViewModel", "Error: ${e.message}")
            }
        }
    }
}