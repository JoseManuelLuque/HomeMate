package com.jluqgon214.hogarmate.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jluqgon214.hogarmate.client.RetrofitClient
import com.jluqgon214.hogarmate.model.Tarea
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.await
import retrofit2.awaitResponse

class HomeViewModel : ViewModel() {
    private val _tareas = MutableStateFlow<List<Tarea>>(emptyList())
    val tareas: StateFlow<List<Tarea>> = _tareas

    private val _tareasCargando = MutableStateFlow(false)
    val tareasCargando: StateFlow<Boolean> = _tareasCargando

    fun obtenerTareas() {
        viewModelScope.launch {
            try {
                _tareasCargando.value = true
                val response = RetrofitClient.instance.obtenerTareasUsuario().awaitResponse()
                if (response.isSuccessful) {
                    _tareas.value = response.body() ?: emptyList()
                } else {
                    // Manejo de errores
                    Log.d("HomeViewModel", "Error al obtener tareas: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                // Manejo de excepciones
                Log.d("HomeViewModel", "Error al obtener tareas: ${e.message}")
            } finally {
                _tareasCargando.value = false
            }
        }
    }
}