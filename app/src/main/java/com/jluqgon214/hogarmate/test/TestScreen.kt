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
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.jluqgon214.hogarmate.components.AddTaskDialog
import com.jluqgon214.hogarmate.components.CardTarea
import com.jluqgon214.hogarmate.viewModel.TasksViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestScreen(tasksViewModel: TasksViewModel, paddingValues: PaddingValues) {
    remember { mutableStateOf(false) }

    val tareas by tasksViewModel.tareas.collectAsState()

    tareas.filter { !it.completada }
    tareas.filter { it.completada }

    LaunchedEffect(Unit) {
        tasksViewModel.obtenerTareas()
    }

    val showDialog by tasksViewModel.showDialog.collectAsState()

    if (showDialog) {
        AddTaskDialog(tasksViewModel, null ,null)
    }

    /*SwipeActionsRight(
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
    }*/

    var itemCount by remember { mutableIntStateOf(15) }
    var isRefreshing by remember { mutableStateOf(false) }
    val state = rememberPullToRefreshState()
    val coroutineScope = rememberCoroutineScope()
    val onRefresh: () -> Unit = {
        isRefreshing = true
        coroutineScope.launch {
            delay(5000)
            itemCount += 5
            isRefreshing = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Title") },
                // Provide an accessible alternative to trigger refresh.
                actions = {
                    IconButton(onClick = onRefresh) {
                        Icon(Icons.Filled.Refresh, "Trigger Refresh")
                    }
                }
            )
        }
    ) {
        PullToRefreshBox(
            modifier = Modifier.padding(it),
            state = state,
            isRefreshing = isRefreshing,
            onRefresh = onRefresh,
        ) {
            LazyColumn(Modifier.fillMaxSize()) {
                items(itemCount) { ListItem({ Text(text = "Item ${itemCount - it}") }) }
            }
        }
    }
}