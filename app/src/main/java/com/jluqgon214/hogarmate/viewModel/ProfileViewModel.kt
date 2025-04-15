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

class ProfileViewModel : ViewModel() {

    private val apiService = RetrofitClient.instance

    private val _usuario = MutableStateFlow<Usuario?>(null)
    val usuario: StateFlow<Usuario?> = _usuario

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _showEditDialog = MutableStateFlow(false)
    val showEditDialog: StateFlow<Boolean> = _showEditDialog

    fun setShowEditDialog(value: Boolean) {
        _showEditDialog.value = value
    }

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    fun setUsername(value: String) {
        _username.value = value
    }

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    fun setEmail(value: String) {
        _email.value = value
    }

    fun obtenerUsuario() {
        viewModelScope.launch {
            try {
                val response = apiService.obtenerUsuario().awaitResponse()
                Log.d("ProfileViewModel", "Response: $response")
                if (response.isSuccessful) {
                    _usuario.value = response.body()
                } else {
                    _errorMessage.value = "Error al obtener usuario: ${response.message()}"
                    Log.e("ProfileViewModel", "Error al obtener usuario: ${response.message()}")
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error de red: ${e.message}"
                Log.e("ProfileViewModel", "Error de red: ${e.message}")
            }
        }
    }

    fun actualizarUsuario() {
        viewModelScope.launch {
            try {
                val usuarioActualizado = Usuario(
                    _usuario.value?._id ?: "",
                    _username.value,
                    _usuario.value?.password ?: "",
                    _email.value,
                    _usuario.value?.roles ?: "",
                    _usuario.value?.hogar
                )
                val response = apiService.actualizarUsuario(usuarioActualizado).awaitResponse()
                if (response.isSuccessful) {
                    _usuario.value = response.body()
                } else {
                    _errorMessage.value = "Error al actualizar usuario: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error de red: ${e.message}"
            }
        }
    }
}