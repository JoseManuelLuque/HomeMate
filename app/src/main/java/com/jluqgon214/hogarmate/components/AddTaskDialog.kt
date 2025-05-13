package com.jluqgon214.hogarmate.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jluqgon214.hogarmate.ui.theme.GreenPrimary
import com.jluqgon214.hogarmate.viewModel.AdminViewModel
import com.jluqgon214.hogarmate.viewModel.TasksViewModel

@Composable
fun AddTaskDialog(
    tasksViewModel: TasksViewModel,
    adminViewModel: AdminViewModel?,
    usuarioId: String?
) {
    val description = tasksViewModel.description.collectAsState().value

    Log.d("Dialog", "idUsuario: $usuarioId")

    AlertDialog(
        onDismissRequest = { tasksViewModel.setShowDialog(false) },
        title = {
            if (usuarioId == null) {
                Text(
                    text = "Agregar Tarea",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            } else {
                Text(
                    text = "Agregar Tarea a Usuario ${usuarioId}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CustomTextField(
                    label = "Descripción de la tarea",
                    value = description,
                    onValueChange = {
                        tasksViewModel.setDescription(it)
                    },
                    placeholder = "Descripción"
                )
            }
        },
        confirmButton = {
            AlertButton(
                onClick = {
                    if (usuarioId == null) {
                        tasksViewModel.agregarTarea()
                        tasksViewModel.setShowDialog(false)
                    } else {
                        tasksViewModel.agregarTareaUsuario(usuarioId)
                        tasksViewModel.setAdminDialog(false)
                        adminViewModel?.obtenerUsuariosConTareas()
                    }
                },
                text = "Agregar",
                textColor = GreenPrimary,
                enabled = description.isNotBlank() // Botón habilitado solo si la descripción no está vacía
            )
        },
        dismissButton = {
            AlertButton(
                onClick = {
                    if (usuarioId == null) {
                        tasksViewModel.setShowDialog(false)
                    } else {
                        tasksViewModel.setAdminDialog(false)
                    }
                },
                text = "Cancelar",
                textColor = Color.Red
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        tonalElevation = 6.dp
    )
}