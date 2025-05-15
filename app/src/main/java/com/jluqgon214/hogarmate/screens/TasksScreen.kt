package com.jluqgon214.hogarmate.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialShapes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.jluqgon214.hogarmate.components.AddTaskDialog
import com.jluqgon214.hogarmate.components.CardTarea
import com.jluqgon214.hogarmate.viewModel.TasksViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Objects
import kotlin.concurrent.thread

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(tasksViewModel: TasksViewModel, paddingValues: PaddingValues, navController: NavController) {
    // val tareas by tasksViewModel.tareas.collectAsState()
    val tareasCargando by tasksViewModel.tareasCargando.collectAsState()

    val tareasPendientes by tasksViewModel.tareasPendientes.collectAsState()
    val tareasCompletadas by tasksViewModel.tareasCompletadas.collectAsState()

    LaunchedEffect(Unit) {
        tasksViewModel.obtenerTareas()
    }

    SideEffect {
        if (tareasCargando){ tasksViewModel.obtenerTareas() }
    }

    val showDialog by tasksViewModel.showDialog.collectAsState()

    if (showDialog) {
        AddTaskDialog(tasksViewModel, adminViewModel = null, null)
    }

    if (tareasCargando) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .zIndex(1f) // Para asegurarse de que el CircularProgressIndicator esté encima de otros elementos
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }


    var isRefreshing by remember { mutableStateOf(false) }
    val state = rememberPullToRefreshState()
    val coroutineScope = rememberCoroutineScope()
    val onRefresh: () -> Unit = {
        isRefreshing = true
        coroutineScope.launch {
            tasksViewModel.obtenerTareas()
            isRefreshing = false
        }
    }


    PullToRefreshBox(
        state = state,
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                )
                {
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        thickness = 4.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text("Tareas Pendientes", Modifier
                        .weight(2f)
                        .wrapContentWidth())
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

            item {
                HorizontalDivider()
            }
            item {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                )
                {
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        thickness = 4.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text("Tareas Completadas", Modifier
                        .weight(2f)
                        .wrapContentWidth())
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

}