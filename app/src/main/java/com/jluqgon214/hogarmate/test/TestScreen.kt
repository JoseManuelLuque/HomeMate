package com.jluqgon214.hogarmate.test

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jluqgon214.hogarmate.components.AddTaskDialog
import com.jluqgon214.hogarmate.components.CardTarea
import com.jluqgon214.hogarmate.viewModel.TasksViewModel

@Composable
fun TestScreen(tasksViewModel: TasksViewModel, paddingValues: PaddingValues) {
    var expanded = remember { mutableStateOf(false) }

    val tareas by tasksViewModel.tareas.collectAsState()
    val tareasCargando by tasksViewModel.tareasCargando.collectAsState()

    val tareasPendientes = tareas.filter { !it.completada }
    val tareasCompletadas = tareas.filter { it.completada }

    LaunchedEffect(Unit) {
        tasksViewModel.obtenerTareas()
    }

    val showDialog by tasksViewModel.showDialog.collectAsState()

    if (showDialog) {
        AddTaskDialog(tasksViewModel, null ,null)
    }

    SwipeActionsRight(
        isExpanded = expanded.value,
        numberOfActions = 1,
        onChangedCard = {
            expanded.value = it
        },
        actionOneColor = Color.Black,
        actionOneText = "Borrar Tarea",
        actionOneImage = Icons.Rounded.Delete,
        actionOneBackColor = Color.Red,
        cardBackground = Color.Cyan,
        actionOneClicked = {
            Log.d("SwipeActionsLeft", "Action One Clicked")
        },
    ) {
        /*Row(
            modifier = Modifier  //Your Custom UI
                .fillMaxWidth()
                .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = "Swipe Actions", color = Color.Black,
                    fontSize = 18.sp
                )

                Text(
                    text = "For more options, swipe the cardddd",
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }

        }*/
        if (showDialog) {
            AddTaskDialog(tasksViewModel, null,null)
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
}