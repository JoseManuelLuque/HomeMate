package com.jluqgon214.hogarmate.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jluqgon214.hogarmate.model.LoginResponse
import com.jluqgon214.hogarmate.repository.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.net.SocketTimeoutException

/**
 * # ViewModel para gestionar el inicio de sesión del usuario.
 *
 * Esta clase utiliza flujos de estado (`StateFlow`) para manejar datos reactivos
 * como el correo electrónico, la contraseña, la respuesta de inicio de sesión,
 * mensajes de error y el estado de carga.
 */
class LoginViewModel : ViewModel() {

    // Repositorio para realizar las operaciones de inicio de sesión.
    private val repository = LoginRepository()

    /**
     * # Flujo que contiene el correo electrónico del usuario.
     */
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    /**
     * # Flujo que contiene la contraseña del usuario.
     */
    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    /**
     * # Flujo que contiene la respuesta de inicio de sesión.
     */
    private val _loginResponse = MutableStateFlow<LoginResponse?>(null)
    val loginResponse: StateFlow<LoginResponse?> = _loginResponse

    /**
     * # Flujo que contiene el mensaje de error en caso de fallo.
     */
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    /**
     * # Flujo que indica si se está realizando una operación de carga.
     */
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    /**
     * # Flujo que indica si el inicio de sesión fue exitoso.
     */
    private val _loginCorrecto = MutableStateFlow<Boolean>(false)
    val loginCorrecto: StateFlow<Boolean> = _loginCorrecto

    /**
     * ### Establece si el inicio de sesión fue exitoso.
     *
     * @param value Valor booleano que indica si el inicio de sesión fue correcto.
     */
    fun setLoginCorrecto(value: Boolean) {
        _loginCorrecto.value = value
    }

    /**
     * ### Establece el correo electrónico del usuario.
     *
     * @param email Correo electrónico del usuario.
     */
    fun setEmail(email: String) {
        _email.value = email
    }

    /**
     * ### Establece la contraseña del usuario.
     *
     * @param password Contraseña del usuario.
     */
    fun setPassword(password: String) {
        _password.value = password
    }

    /**
     * ### Establece el mensaje de error.
     *
     * @param message Mensaje de error.
     */
    fun setErrorMessage(message: String?) {
        _errorMessage.value = message
    }

    /**
     * ## Objeto `companion` para gestionar el token JWT de forma global.
     *
     * Este objeto permite almacenar y acceder al token JWT sin necesidad de instanciar
     * la clase `LoginViewModel`.
     */
    companion object {
        /**
         * # Flujo que contiene el token JWT.
         */
        private val _JWT = MutableStateFlow<String?>(null)

        /**
         * # Versión de solo lectura del token JWT.
         */
        val JWT: StateFlow<String?> = _JWT

        /**
         * ### Obtiene el valor actual del token JWT.
         *
         * @return El token JWT actual o `null` si no está definido.
         */
        fun getJWT(): String? {
            return _JWT.value
        }
    }

    /**
     * ### Establece el valor del token JWT.
     *
     * @param jwt Token JWT a establecer.
     */
    fun setJWT(jwt: String?) {
        _JWT.value = jwt
    }

    /**
     * ### Realiza el inicio de sesión del usuario.
     *
     * Esta función realiza una llamada a la API para autenticar al usuario
     * utilizando el correo electrónico y la contraseña proporcionados.
     * Actualiza los flujos de estado con la respuesta o los errores.
     */
    fun login() {
        viewModelScope.launch {
            // Indica que se está cargando
            _isLoading.value = true
            try {
                // Llama a la función de inicio de sesión en el repositorio
                val response: Response<LoginResponse> =
                    repository.login(_email.value, _password.value)

                // Comprueba si la respuesta es exitosa
                if (response.isSuccessful) {
                    // Actualiza el flujo de respuesta de inicio de sesión
                    _loginResponse.value = response.body()
                    setJWT(response.body()?.token) // Guarda el token JWT
                    setLoginCorrecto(true) // Indica que el inicio de sesión fue exitoso
                } else {
                    // Maneja el error de la respuesta
                    val errorJson = response.errorBody()?.string()
                    errorJson?.let {
                        try {
                            // Analiza el cuerpo de error JSON
                            val jsonObject = JSONObject(it)
                            val errorMessage = jsonObject.getString("message")
                            _errorMessage.value = errorMessage
                        } catch (e: JSONException) {
                            // Maneja errores de análisis JSON
                            _errorMessage.value = it

                            // Log para verificar el error
                            Log.e(
                                "LoginViewModel",
                                "Error al analizar el JSON de error: ${e.message}"
                            )
                        }
                    } ?: run {
                        // Si no se puede analizar el cuerpo de error, establece un mensaje genérico
                        _errorMessage.value = "Inicio de sesión fallido"
                        // Log para verificar el error
                        Log.e("LoginViewModel", "Error al obtener el cuerpo de error: $errorJson")
                    }
                }
            } catch (e: SocketTimeoutException) {
                // Maneja excepciones de tiempo de espera
                _errorMessage.value = "Comprueba tu conexión a Internet"

                // Log para verificar la excepción
                Log.e("LoginViewModel", "Error de tiempo de espera: ${e.message}")
            } catch (e: Exception) {
                // Maneja otras excepciones
                _errorMessage.value = "Request failed: ${e.message}"
                // Log para verificar la excepción
                Log.e("LoginViewModel", "Error de red: ${e.message}")
            } finally {
                // Finaliza el estado de carga
                _isLoading.value = false
            }
        }
    }
}