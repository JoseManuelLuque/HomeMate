package com.jluqgon214.hogarmate.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jluqgon214.hogarmate.client.RetrofitClient
import com.jluqgon214.hogarmate.model.DTO.CrearTareaDTO
import com.jluqgon214.hogarmate.model.DTO.UsuarioConTareasDTO
import com.jluqgon214.hogarmate.model.Tarea
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

/**
 * # ViewModel para gestionar las tareas de los usuarios.
 */
class TasksViewModel : ViewModel() {

    /**
     * Flujo que contiene la lista de tareas.
     */
    private val _tareas = MutableStateFlow<List<Tarea>>(emptyList())
    val tareas: StateFlow<List<Tarea>> = _tareas

    /**
     * Flujo que indica si las tareas están cargando.
     */
    private val _tareasCargando = MutableStateFlow(false)
    val tareasCargando: StateFlow<Boolean> = _tareasCargando

    /**
     * Flujo que controla la visibilidad del diálogo de creación de tareas.
     */
    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog

    /**
     * Flujo que contiene la descripción de la tarea.
     */
    private val _description = MutableStateFlow("")
    val description: StateFlow<String> = _description

    /**
     * Flujo que controla la visibilidad del diálogo de administración.
     */
    private val _adminDialog = MutableStateFlow(false)
    val adminDialog: StateFlow<Boolean> = _adminDialog

    /**
     * Flujo que contiene la información del usuario con sus tareas.
     */
    private val _usuario = MutableStateFlow<UsuarioConTareasDTO?>(null)
    val usuario: MutableStateFlow<UsuarioConTareasDTO?> = _usuario

    /**
     * ### Establece el usuario actual.
     *
     * @param usuario El usuario con sus tareas.
     */
    fun setUsuario(usuario: UsuarioConTareasDTO) {
        _usuario.value = usuario
    }

    /**
     * ### Controla la visibilidad del diálogo de administración.
     *
     * @param show `true` para mostrar el diálogo, `false` para ocultarlo.
     */
    fun setAdminDialog(show: Boolean) {
        _adminDialog.value = show
    }

    /**
     * ### Controla la visibilidad del diálogo de creación de tareas.
     *
     * @param show `true` para mostrar el diálogo, `false` para ocultarlo.
     */
    fun setShowDialog(show: Boolean) {
        _showDialog.value = show
    }

    /**
     * ### Establece la descripción de la tarea.
     *
     * @param description La descripción de la tarea.
     */
    fun setDescription(description: String) {
        _description.value = description
    }

    /**
     * Flujo que contiene las tareas pendientes.
     */
    private val _tareasPendientes = MutableStateFlow<List<Tarea>>(emptyList())
    val tareasPendientes: MutableStateFlow<List<Tarea>> = _tareasPendientes

    /**
     * Flujo que contiene las tareas completadas.
     */
    private val _tareasCompletadas = MutableStateFlow<List<Tarea>>(emptyList())
    val tareasCompletadas: MutableStateFlow<List<Tarea>> = _tareasCompletadas

    /**
     * ### Filtra las tareas en pendientes y completadas.
     */
    fun filtrarTareas() {
        // Filtrar las tareas pendientes y completadas
        viewModelScope.launch {
            _tareasPendientes.value = _tareas.value.filter { !it.completada }
            _tareasCompletadas.value = _tareas.value.filter { it.completada }
        }
    }

    /**
     * ### Obtiene las tareas del usuario desde el servidor.
     */
    fun obtenerTareas() {
        viewModelScope.launch {
            try {
                // Indicar que las tareas están cargando
                _tareasCargando.value = true

                // Llamada a la API para obtener las tareas
                val response = RetrofitClient.instance.obtenerTareasUsuario().awaitResponse()

                // Verificar si la respuesta es exitosa
                if (response.isSuccessful) {
                    // Actualizar la lista de tareas
                    _tareas.value = response.body() ?: emptyList()
                    filtrarTareas() // Filtra las tareas después de obtenerlas
                } else {
                    // Log para verificar el error
                    Log.d(
                        "TasksViewModel",
                        "Error al obtener tareas: ${response.errorBody()?.string()}"
                    )
                }
            } catch (e: Exception) {
                // Manejar excepciones de red y generar logs
                Log.d("TasksViewModel", "Error al obtener tareas: ${e.message}")
            } finally {
                // Indicar que las tareas han dejado de cargar
                _tareasCargando.value = false
            }
        }
    }

    /**
     * ### Agrega una nueva tarea con la descripción actual.
     */
    fun agregarTarea() {
        viewModelScope.launch {
            try {
                // Crear el objeto DTO para la tarea con la descripción actual
                val tareaDTO = CrearTareaDTO(description.value)

                // Llamada a la API para crear la tarea
                val response = RetrofitClient.instance.crearTarea(tareaDTO).awaitResponse()

                // Verificar si la respuesta es exitosa
                if (response.isSuccessful) {
                    obtenerTareas() // Actualiza la lista de tareas después de agregar
                } else {
                    // Log para verificar el error
                    Log.d(
                        "TasksViewModel",
                        "Error al agregar tarea: ${response.errorBody()?.string()}"
                    )
                }
            } catch (e: Exception) {
                // Manejar excepciones de red y generar logs
                Log.d("TasksViewModel", "Error al agregar tarea: ${e.message}")
            }
        }
    }

    /**
     * ### Agrega una nueva tarea para un usuario específico.
     *
     * @param idUsuario El ID del usuario al que se le asignará la tarea.
     */
    fun agregarTareaUsuario(idUsuario: String?) {
        viewModelScope.launch {
            try {
                val tareaDTO = CrearTareaDTO(description.value)
                val response =
                    RetrofitClient.instance.crearTareaUsuario(idUsuario, tareaDTO).awaitResponse()
                if (response.isSuccessful) {
                    obtenerTareas() // Actualiza la lista de tareas después de agregar
                    Log.d(
                        "TasksViewModel",
                        "Tarea agregada con éxito, Description: ${description.value}, Usuario: $idUsuario"
                    )
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

    /**
     * ### Elimina una tarea por su ID.
     *
     * @param id El ID de la tarea a eliminar.
     */
    fun eliminarTarea(id: String?) {
        viewModelScope.launch {
            try {
                // Llamada a la API para eliminar la tarea
                val response = RetrofitClient.instance.eliminarTarea(id).awaitResponse()

                // Verificar si la respuesta es exitosa
                if (response.isSuccessful) {
                    obtenerTareas() // Actualiza la lista de tareas después de eliminar
                } else {
                    // Log para verificar el error
                    Log.d(
                        "TasksViewModel",
                        "Error al eliminar tarea: ${response.errorBody()?.string()}"
                    )
                }
            } catch (e: Exception) {
                // Manejar excepciones de red y generar logs
                Log.d("TasksViewModel", "Error al eliminar tarea: ${e.message}")
            }
        }
    }

    /**
     * ### Actualiza el estado de una tarea por su ID.
     *
     * @param id El ID de la tarea a actualizar.
     */
    fun actualizarEstadoTarea(id: String?) {
        viewModelScope.launch {
            try {
                // Llamada a la API para actualizar el estado de la tarea
                val response = RetrofitClient.instance.actualizarEstadoTarea(id).awaitResponse()

                // Verificar si la respuesta es exitosa
                if (response.isSuccessful) {
                    obtenerTareas() // Actualiza la lista de tareas después de actualizar
                } else {
                    // Log para verificar el error
                    Log.d(
                        "TasksViewModel",
                        "Error al actualizar tarea: ${response.errorBody()?.string()}"
                    )
                }
            } catch (e: Exception) {
                // Manejar excepciones de red y generar logs
                Log.d("TasksViewModel", "Error al actualizar tarea: ${e.message}")
            }
        }
    }
}