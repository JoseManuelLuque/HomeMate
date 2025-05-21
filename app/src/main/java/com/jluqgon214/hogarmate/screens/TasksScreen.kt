package com.jluqgon214.hogarmate.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.jluqgon214.hogarmate.components.AddTaskDialog
import com.jluqgon214.hogarmate.components.CardTarea
import com.jluqgon214.hogarmate.viewModel.TasksViewModel

/**
 * # Pantalla de tareas.
 *
 * Esta pantalla muestra las tareas pendientes y completadas del usuario. Permite realizar acciones
 * como actualizar el estado de una tarea, eliminarla o añadir nuevas tareas mediante un diálogo.
 * También incluye funcionalidad de "pull-to-refresh" para recargar las tareas.
 *
 * @param tasksViewModel ViewModel que gestiona la lógica de las tareas.
 * @param paddingValues Espaciado externo aplicado a los elementos de la pantalla.
 * @param navController Controlador de navegación para gestionar la navegación entre pantallas.
 */
@Composable
fun TasksScreen(
    tasksViewModel: TasksViewModel,
    paddingValues: PaddingValues,
    navController: NavController
) {
    // Estado que indica si las tareas están cargando.
    val tareasCargando by tasksViewModel.tareasCargando.collectAsState()

    // Listas de tareas pendientes y completadas.
    val tareasPendientes by tasksViewModel.tareasPendientes.collectAsState()
    val tareasCompletadas by tasksViewModel.tareasCompletadas.collectAsState()

    // Efecto lanzado al inicializar la pantalla para obtener las tareas.
    LaunchedEffect(Unit) {
        tasksViewModel.obtenerTareas()
    }

    // Efecto secundario para recargar las tareas.
    SideEffect {
        if (tareasCargando) {
            tasksViewModel.obtenerTareas()
        }
    }

    // Estado que indica si el diálogo para añadir tareas está visible.
    val showDialog by tasksViewModel.showDialog.collectAsState()

    // Muestra el diálogo para añadir tareas si está activo.
    if (showDialog) {
        AddTaskDialog(tasksViewModel, adminViewModel = null, null)
    }

    // Muestra un indicador de carga si las tareas están cargando.
    if (tareasCargando) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .zIndex(1f) // Asegura que el indicador esté encima de otros elementos.
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        // Sección de tareas pendientes.
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    thickness = 4.dp,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    "Tareas Pendientes", Modifier
                        .weight(2f)
                        .wrapContentWidth()
                )
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    thickness = 4.dp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        for (tarea in tareasPendientes) {
            item {
                CardTarea(
                    tarea, tarea.usuario.username, {
                        tasksViewModel.actualizarEstadoTarea(tarea._id)
                        tasksViewModel.obtenerTareas()
                        navController.navigate("tasksScreen")
                    }, {
                        tasksViewModel.eliminarTarea(tarea._id)
                        tasksViewModel.obtenerTareas()
                        navController.navigate("tasksScreen")
                    }
                )
            }
        }

        // Separador entre secciones.
        item {
            HorizontalDivider()
        }

        // Sección de tareas completadas.
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    thickness = 4.dp,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    "Tareas Completadas", Modifier
                        .weight(2f)
                        .wrapContentWidth()
                )
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    thickness = 4.dp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        for (tarea in tareasCompletadas) {
            item {
                CardTarea(
                    tarea, tarea.usuario.username, {
                        tasksViewModel.actualizarEstadoTarea(tarea._id)
                        tasksViewModel.obtenerTareas()
                        navController.navigate("tasksScreen")
                    }, {
                        tasksViewModel.eliminarTarea(tarea._id)
                        tasksViewModel.obtenerTareas()
                        navController.navigate("tasksScreen")
                    }
                )
            }
        }
    }

}