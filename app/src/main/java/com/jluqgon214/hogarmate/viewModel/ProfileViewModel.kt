package com.jluqgon214.hogarmate.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jluqgon214.hogarmate.client.RetrofitClient
import com.jluqgon214.hogarmate.model.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

/**
 * # ViewModel para gestionar el perfil del usuario.
 */
class ProfileViewModel : ViewModel() {

    // Variables de estado para gestionar la información del usuario y errores

    /**
     * Flujo que contiene la información del usuario actual.
     */
    private val _usuario = MutableStateFlow<Usuario?>(null)
    val usuario: StateFlow<Usuario?> = _usuario

    /**
     * Flujo que contiene el mensaje de error en caso de que ocurra un problema.
     */
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    /**
     * Flujo que indica si se debe mostrar el diálogo de edición.
     */
    private val _showEditDialog = MutableStateFlow(false)
    val showEditDialog: StateFlow<Boolean> = _showEditDialog

    /**
     * Flujo que contiene el nombre de usuario.
     */
    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    /**
     * Flujo que contiene el correo electrónico del usuario.
     */
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    // Funciones para establecer el nombre de usuario, el correo electrónico y el diálogo de edición

    /**
     * ### Establece el nombre de usuario.
     *
     * @param value Nombre de usuario.
     */
    fun setUsername(value: String) {
        _username.value = value
    }

    /**
     * ### Establece el correo electrónico del usuario.
     *
     * @param value Correo electrónico.
     */
    fun setEmail(value: String) {
        _email.value = value
    }

    /**
     * ### Establece si se debe mostrar el diálogo de edición.
     *
     * @param value Valor booleano que indica si se muestra el diálogo.
     */
    fun setShowEditDialog(value: Boolean) {
        _showEditDialog.value = value
    }


    // Funciones para obtener y actualizar el usuario

    /**
     * ### Obtiene la información del usuario desde la API.
     *
     * Realiza una llamada a la API para obtener los datos del usuario y actualiza
     * el flujo de estado `_usuario`. En caso de error, actualiza `_errorMessage`.
     */
    fun obtenerUsuario() {
        viewModelScope.launch {
            try {
                // Llamada a la API para obtener el usuario
                val response = RetrofitClient.instance.obtenerUsuario().awaitResponse()

                // Log para verificar la respuesta
                Log.d("ProfileViewModel", "Response: $response")

                // Verificar si la respuesta es exitosa
                if (response.isSuccessful) {
                    // Asignar el usuario obtenido a la variable de estado
                    _usuario.value = response.body()
                } else {
                    // Manejar el error de la respuesta
                    _errorMessage.value = "Error al obtener usuario: ${response.message()}"

                    // Log para verificar el error
                    Log.e("ProfileViewModel", "Error al obtener usuario: ${response.message()}")
                }
            } catch (e: Exception) {
                // Manejar excepciones de red
                _errorMessage.value = "Error de red: ${e.message}"

                // Log para verificar la excepción
                Log.e("ProfileViewModel", "Error de red: ${e.message}")
            }
        }
    }

    /**
     * ### Actualiza la información del usuario en la API.
     *
     * Crea un objeto `Usuario` con los datos actualizados y realiza una llamada
     * a la API para actualizar la información. Actualiza el flujo `_usuario` si
     * la operación es exitosa o `_errorMessage` en caso de error.
     */
    fun actualizarUsuario() {
        viewModelScope.launch {
            try {
                // Crear un objeto Usuario con los datos actualizados
                val usuarioActualizado = Usuario(
                    _usuario.value?._id ?: "",
                    _username.value,
                    _usuario.value?.password ?: "",
                    _email.value,
                    _usuario.value?.roles ?: "",
                    _usuario.value?.hogar
                )

                // Llamada a la API para actualizar el usuario
                val response =
                    RetrofitClient.instance.actualizarUsuario(usuarioActualizado).awaitResponse()

                // Si la respuesta es exitosa, actualiza el flujo de estado con el usuario actualizado
                if (response.isSuccessful) {
                    _usuario.value = response.body()
                } else {
                    // Manejar el error de la respuesta
                    _errorMessage.value = "Error al actualizar usuario: ${response.message()}"

                    // Log para verificar el error
                    Log.e("ProfileViewModel", "Error al actualizar usuario: ${response.message()}")
                }
            } catch (e: Exception) {
                // Manejar excepciones de red
                _errorMessage.value = "Error de red: ${e.message}"

                // Log para verificar la excepción
                Log.e("ProfileViewModel", "Error de red: ${e.message}")
            }
        }
    }
}