package com.jluqgon214.hogarmate.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jluqgon214.hogarmate.client.RetrofitClient
import com.jluqgon214.hogarmate.model.DTO.UsuarioConTareasDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class AdminViewModel : ViewModel() {
    private val _usuariosConTareas = MutableStateFlow<List<UsuarioConTareasDTO>>(emptyList())
    val usuariosConTareas = _usuariosConTareas

    private val _usuariosCargando = MutableStateFlow(false)
    val usuariosCargando = _usuariosCargando

    var _idUsuarioActual = MutableStateFlow<String?>(null)
    val idUsuarioActual = _idUsuarioActual

    fun setUsuarioActualId(id: String) {
        _idUsuarioActual.value = id
    }

    // Método para obtener todos los usuarios con tareas
    fun obtenerUsuariosConTareas() {
        viewModelScope.launch {
            try {
                _usuariosCargando.value = true
                val respuestaApi =
                    RetrofitClient.instance.obtenerUsuariosConTareas().awaitResponse()
                if (respuestaApi.isSuccessful) {
                    _usuariosConTareas.value = respuestaApi.body() ?: emptyList()

                    // Filtrar usuarios para excluir al usuario actual
                    _usuariosConTareas.value =
                        _usuariosConTareas.value.filter { it._id != idUsuarioActual.value }


                    Log.d("AdminViewModel", "Usuarios con tareas: ${_usuariosConTareas.value}")
                } else {
                    _usuariosConTareas.value = emptyList()
                }
            } catch (e: Exception) {
                _usuariosConTareas.value = emptyList()
                Log.e("AdminViewModel", "Error al obtener usuarios con tareas: ${e.message}")
            } finally {
                _usuariosCargando.value = false
            }
        }
    }

    /**
     * Flujo que indica si el usuario es administrador.
     */
    private val _isAdmin = MutableStateFlow(false)
    val isAdmin: StateFlow<Boolean> = _isAdmin

    /**
     * ### Comprueba si el usuario es administrador.
     *
     * Realiza una llamada a la API para verificar si el usuario tiene permisos de administrador
     * y actualiza el estado del flujo `_isAdmin` en consecuencia.
     */
    fun comprobarAdmin() {
        viewModelScope.launch {
            try {
                // Llamada a la API para comprobar si el usuario es administrador
                val response = RetrofitClient.instance.comprobarAdmin().awaitResponse()

                // Si la respuesta es exitosa, actualizamos el estado
                if (response.isSuccessful) {
                    _isAdmin.value = response.body() == true
                } else {
                    // Si no es exitosa, sabemos que no es admin
                    _isAdmin.value = false
                }
            } catch (e: Exception) {
                // Manejar el error de la llamada a la API
                // Si falla por si acaso ponemos que no es admin
                _isAdmin.value = false

                // Log para verificar el error
                Log.e(
                    "NavigationViewModel",
                    "Error al comprobar si el usuario es administrador: ${e.message}"
                )
            }
        }
    }
}
