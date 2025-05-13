package com.jluqgon214.hogarmate.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jluqgon214.hogarmate.components.AddTaskDialog
import com.jluqgon214.hogarmate.components.UsuarioItem
import com.jluqgon214.hogarmate.viewModel.AdminViewModel
import com.jluqgon214.hogarmate.viewModel.TasksViewModel

@Composable
fun AdminScreen(
    adminViewModel: AdminViewModel,
    paddingValues: PaddingValues,
    tasksViewModel: TasksViewModel
) {
    val usuariosConTareas by adminViewModel.UsuariosConTareas.collectAsState()

    val adminDialog by tasksViewModel.adminDialog.collectAsState()

    val usuarioId by tasksViewModel.usuarioId.collectAsState()

    val usuariosCargando by adminViewModel.usuariosCargando.collectAsState()

    LaunchedEffect(Unit) {
        adminViewModel.obtenerUsuariosConTareas()
    }

    if (adminDialog) {
        AddTaskDialog(
            tasksViewModel = tasksViewModel,
            adminViewModel = adminViewModel,
            usuarioId = usuarioId,
        )
    }

    if (usuariosCargando) {
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
                .fillMaxWidth()
                .padding(paddingValues = paddingValues)
        ) {
            items(usuariosConTareas) { usuario ->
                UsuarioItem(usuario, tasksViewModel)
            }
        }
    }
}