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

/**
 * # ViewModel para gestionar la administración de usuarios y sus tareas.
 */
class AdminViewModel : ViewModel() {
    /**
     * Flujo que contiene la lista de usuarios con sus tareas.
     */
    private val _usuariosConTareas = MutableStateFlow<List<UsuarioConTareasDTO>>(emptyList())
    val usuariosConTareas = _usuariosConTareas

    /**
     * Flujo que indica si los usuarios están siendo cargados.
     */
    private val _usuariosCargando = MutableStateFlow(false)
    val usuariosCargando = _usuariosCargando

    /**
     * Flujo que contiene el ID del usuario actual.
     */
    private val _idUsuarioActual = MutableStateFlow<String?>(null)
    val idUsuarioActual = _idUsuarioActual

    /**
     * Establece el ID del usuario actual.
     *
     * @param id ID del usuario actual.
     */
    fun setUsuarioActualId(id: String) {
        _idUsuarioActual.value = id
    }

    /**
     * ### Obtiene la lista de usuarios con sus tareas desde la API.
     *
     * Realiza una llamada a la API para obtener los usuarios con tareas asignadas,
     * excluyendo al usuario actual, y actualiza el estado del flujo `_usuariosConTareas`.
     * También actualiza el estado de carga en `_usuariosCargando`.
     */
    fun obtenerUsuariosConTareas() {
        viewModelScope.launch {
            try {
                // Indicamos que estamos cargando los usuarios
                _usuariosCargando.value = true

                // Llamada a la API para obtener usuarios con tareas
                val respuestaApi =
                    RetrofitClient.instance.obtenerUsuariosConTareas().awaitResponse()

                // Verificamos si la respuesta es exitosa
                if (respuestaApi.isSuccessful) {
                    // Asignamos la lista de usuarios a la variable de estado
                    _usuariosConTareas.value = respuestaApi.body() ?: emptyList()

                    // Filtrar usuarios para excluir al usuario actual
                    _usuariosConTareas.value =
                        _usuariosConTareas.value.filter { it._id != idUsuarioActual.value }

                    // Log para verificar los usuarios obtenidos
                    Log.d("AdminViewModel", "Usuarios con tareas: ${_usuariosConTareas.value}")
                } else {
                    // Si la respuesta no es exitosa, asignamos una lista vacía
                    _usuariosConTareas.value = emptyList()

                    // Log para verificar el error
                    Log.e(
                        "AdminViewModel",
                        "Error al obtener usuarios con tareas: ${respuestaApi.errorBody()}"
                    )
                }
            } catch (e: Exception) {
                // Manejar el error de la llamada a la API
                _usuariosConTareas.value = emptyList()

                // Log para verificar el error
                Log.e("AdminViewModel", "Error al obtener usuarios con tareas: ${e.message}")
            } finally {
                // Indicamos que hemos terminado de cargar los usuarios
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
                    "AdminViewModel",
                    "Error al comprobar si el usuario es administrador: ${e.message}"
                )
            }
        }
    }
}
