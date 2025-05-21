package com.jluqgon214.hogarmate.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jluqgon214.hogarmate.model.DTO.UsuarioConTareasDTO
import com.jluqgon214.hogarmate.viewModel.AdminViewModel
import com.jluqgon214.hogarmate.viewModel.TasksViewModel

/**
 * # Diálogo para agregar una nueva tarea, ya sea para el usuario actual o para un usuario específico.
 */
@Composable
fun AddTaskDialog(
    tasksViewModel: TasksViewModel,
    adminViewModel: AdminViewModel?,
    usuario: UsuarioConTareasDTO?
) {
    // Estado que contiene la descripción de la tarea.
    val description = tasksViewModel.description.collectAsState().value

    AlertDialog(
        onDismissRequest = { tasksViewModel.setShowDialog(false) }, // Acción al cerrar el diálogo.
        title = {
            if (usuario == null) {
                // Título del diálogo para el usuario actual.
                Text(
                    text = "Agregar Tarea",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            } else {
                // Título del diálogo para un usuario específico.
                Text(
                    text = "Agregar Tarea a Usuario ${usuario.username}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Campo de texto personalizado para ingresar la descripción de la tarea.
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
            // Botón para confirmar la acción de agregar tarea.
            AlertButton(
                onClick = {
                    if (usuario == null) {
                        // Agrega la tarea al usuario actual.
                        tasksViewModel.agregarTarea()
                        tasksViewModel.setShowDialog(false)
                    } else {
                        // Agrega la tarea a un usuario específico.
                        tasksViewModel.agregarTareaUsuario(usuario._id)
                        tasksViewModel.setAdminDialog(false)
                        adminViewModel?.obtenerUsuariosConTareas()
                    }
                },
                text = "Agregar",
                textColor = MaterialTheme.colorScheme.primary,
                enabled = description.isNotBlank() // Habilita el botón solo si la descripción no está vacía.
            )
        },
        dismissButton = {
            // Botón para cancelar la acción y cerrar el diálogo.
            AlertButton(
                onClick = {
                    if (usuario == null) {
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
        shape = RoundedCornerShape(16.dp), // Bordes redondeados del diálogo.
        tonalElevation = 6.dp // Elevación tonal para el efecto de sombra.
    )
}