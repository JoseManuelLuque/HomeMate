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


    // Método para obtener todos los usuarios con tareas
    fun obtenerUsuariosConTareas() {
        viewModelScope.launch {
            try {
                _usuariosCargando.value = true
                val respuestaApi =
                    RetrofitClient.instance.obtenerUsuariosConTareas().awaitResponse()
                if (respuestaApi.isSuccessful) {
                    _UsuariosConTareas.value = respuestaApi.body() ?: emptyList()
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
