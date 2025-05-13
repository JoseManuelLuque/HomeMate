package com.jluqgon214.hogarmate.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jluqgon214.hogarmate.components.AddTaskDialog
import com.jluqgon214.hogarmate.components.CardTarea
import com.jluqgon214.hogarmate.viewModel.TasksViewModel

@Composable
fun TasksScreen(tasksViewModel: TasksViewModel, paddingValues: PaddingValues) {
    val tareas by tasksViewModel.tareas.collectAsState()
    val tareasCargando by tasksViewModel.tareasCargando.collectAsState()

    val tareasPendientes = tareas.filter { !it.completada }
    val tareasCompletadas = tareas.filter { it.completada }

    LaunchedEffect(Unit) {
        tasksViewModel.obtenerTareas()
    }

    val showDialog by tasksViewModel.showDialog.collectAsState()

    if (showDialog) {
        AddTaskDialog(tasksViewModel, null)
    }

    if (tareasCargando) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {

                Text("Tareas Pendientes")

            }
            for (tarea in tareasPendientes) {
                item {
                    CardTarea(
                        tarea, tarea.usuario.username, {
                            tasksViewModel.actualizarEstadoTarea(tarea._id)
                        }, {
                            tasksViewModel.eliminarTarea(tarea._id)
                        }
                    )

                }
            }

            item{
                HorizontalDivider()
            }

            item {

                Text("Tareas Completadas")

            }
            for (tarea in tareasCompletadas) {
                item {
                    CardTarea(
                        tarea, tarea.usuario.username, {
                            tasksViewModel.actualizarEstadoTarea(tarea._id)
                        }, {
                            tasksViewModel.eliminarTarea(tarea._id)
                        }
                    )

                }
            }
        }
    }
}