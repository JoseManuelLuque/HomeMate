package com.jluqgon214.hogarmate.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jluqgon214.hogarmate.client.RetrofitClient
import com.jluqgon214.hogarmate.model.DTO.CrearTareaDTO
import com.jluqgon214.hogarmate.model.Tarea
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class TasksViewModel : ViewModel() {
    private val _tareas = MutableStateFlow<List<Tarea>>(emptyList())
    val tareas: StateFlow<List<Tarea>> = _tareas

    private val _tareasCargando = MutableStateFlow(false)
    val tareasCargando: StateFlow<Boolean> = _tareasCargando

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog

    private val _description = MutableStateFlow("")
    val description: StateFlow<String> = _description

    fun setShowDialog(show: Boolean) {
        _showDialog.value = show
    }

    fun setDescription(description: String) {
        _description.value = description
    }

    // Método para obtener todas las tareas
    fun obtenerTareas() {
        viewModelScope.launch {
            try {
                _tareasCargando.value = true
                val response = RetrofitClient.instance.obtenerTareasUsuario().awaitResponse()
                if (response.isSuccessful) {
                    val tareasObtenidas = response.body()
                    Log.d("TasksViewModel", "Tareas obtenidas: $tareasObtenidas")
                    _tareas.value = tareasObtenidas ?: emptyList()
                } else {
                    Log.d(
                        "TasksViewModel",
                        "Error al obtener tareas: ${response.errorBody()?.string()}"
                    )
                }
            } catch (e: Exception) {
                Log.d("TasksViewModel", "Error al obtener tareas: ${e.message}")
            } finally {
                _tareasCargando.value = false
            }
        }
    }

    // Método para agregar una tarea
    fun agregarTarea() {
        viewModelScope.launch {
            try {
                val tareaDTO = CrearTareaDTO(description.value)
                val response = RetrofitClient.instance.crearTarea(tareaDTO).awaitResponse()
                if (response.isSuccessful) {
                    obtenerTareas() // Actualiza la lista de tareas después de agregar
                } else {
                    Log.d(
                        "TasksViewModel",
                        "Error al agregar tarea: ${response.errorBody()?.string()}"
                    )
                }
            } catch (e: Exception) {
                Log.d("TasksViewModel", "Error al agregar tarea: ${e.message}")
            }
        }
    }

    fun eliminarTarea(id: String?) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.eliminarTarea(id)
                if (response.isSuccessful) {
                    obtenerTareas() // Actualiza la lista de tareas después de eliminar
                } else {
                    Log.d("TasksViewModel", "Error al eliminar tarea: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.d("TasksViewModel", "Error al eliminar tarea: ${e.message}")
            }
        }
    }
}