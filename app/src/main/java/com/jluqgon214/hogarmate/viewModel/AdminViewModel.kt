package com.jluqgon214.hogarmate.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jluqgon214.hogarmate.client.RetrofitClient
import com.jluqgon214.hogarmate.model.DTO.UsuarioConTareasDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class AdminViewModel : ViewModel() {
    private val _UsuariosConTareas = MutableStateFlow<List<UsuarioConTareasDTO>>(emptyList())
    val UsuariosConTareas = _UsuariosConTareas

    private val _usuariosCargando = MutableStateFlow(false)
    val usuariosCargando = _usuariosCargando

    var _usuarioActualId = MutableStateFlow<String?>(null) // Usuario que ha iniciado sesión y se encuentra usando al aplicacion
    val usuarioActualId = _usuarioActualId

    fun setUsuarioActualId(id: String) {
        _usuarioActualId.value = id
    }

    // Método para obtener todos los usuarios con tareas
    fun obtenerUsuariosConTareas() {
        viewModelScope.launch {
            try {
                _usuariosCargando.value = true
                val respuestaApi =
                    RetrofitClient.instance.obtenerUsuariosConTareas().awaitResponse()
                if (respuestaApi.isSuccessful) {
                    _UsuariosConTareas.value = respuestaApi.body() ?: emptyList()

                    // Filtrar usuarios para excluir al usuario actual
                    _UsuariosConTareas.value = _UsuariosConTareas.value.filter { it._id != usuarioActualId.value }


                    Log.d("AdminViewModel", "Usuarios con tareas: ${_UsuariosConTareas.value}")
                } else {
                    _UsuariosConTareas.value = emptyList()
                }
            } catch (e: Exception) {
                _UsuariosConTareas.value = emptyList()
                Log.e("AdminViewModel", "Error al obtener usuarios con tareas: ${e.message}")
            } finally {
                _usuariosCargando.value = false
            }
        }
    }
}
