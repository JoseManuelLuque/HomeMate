package com.jluqgon214.hogarmate.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jluqgon214.hogarmate.model.DTO.UsuarioConTareasDTO
import com.jluqgon214.hogarmate.viewModel.AdminViewModel
import com.jluqgon214.hogarmate.viewModel.TasksViewModel

/**
 * # Componente que representa un elemento de usuario con sus tareas asociadas.
 *
 * Este componente muestra información básica del usuario, como su nombre de usuario y correo electrónico,
 * y permite expandir para ver y gestionar las tareas asociadas al usuario.
 *
 * @param usuario Objeto que contiene la información del usuario y sus tareas.
 * @param tasksViewModel ViewModel que gestiona las acciones relacionadas con las tareas.
 * @param adminViewModel ViewModel que gestiona las acciones relacionadas con los usuarios y sus tareas.
 */
@Composable
fun UsuarioItem(
    usuario: UsuarioConTareasDTO,
    tasksViewModel: TasksViewModel,
    adminViewModel: AdminViewModel
) {
    // Estado que controla si el contenido expandido está visible.
    var expanded by remember { mutableStateOf(false) }

    // Estado que indica si se debe actualizar la lista de usuarios y tareas.
    var actualizar by remember { mutableStateOf(false) }

    // Efecto secundario para obtener la lista de usuarios con tareas al inicializar el componente.
    SideEffect {
        adminViewModel.obtenerUsuariosConTareas()
    }

    Card(
        modifier = Modifier
            .fillMaxWidth() // El ancho del componente ocupa todo el espacio disponible.
            .padding(8.dp) // Espaciado externo del componente.
            .clickable { expanded = !expanded }, // Alterna el estado expandido al hacer clic.
        shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp), // Forma redondeada de la tarjeta.
        elevation = androidx.compose.material3.CardDefaults.cardElevation(8.dp) // Elevación de la tarjeta.
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // Sección que muestra el nombre de usuario y el correo electrónico.
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = usuario.username, // Nombre de usuario.
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = usuario.email, // Correo electrónico del usuario.
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(
                    onClick = {
                        tasksViewModel.setAdminDialog(true) // Abre el diálogo de administración.
                        tasksViewModel.setUsuario(usuario) // Establece el usuario seleccionado.
                        actualizar = true
                    },
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add, // Ícono para agregar o editar.
                        contentDescription = "Eliminar Usuario"
                    )
                }
            }

            // Contenido expandido que muestra las tareas del usuario.
            if (expanded) {
                Column(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                        .background(
                            MaterialTheme.colorScheme.surface,
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
                        )
                        .padding(12.dp)
                ) {
                    Row {
                        Text(
                            text = "Tareas:", // Título de la sección de tareas.
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    usuario.tareas.forEach { tarea ->
                        val icon = if (tarea.completada) Icons.Default.Close else Icons.Default.Done

                        Row(
                            modifier = Modifier.wrapContentSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "- ${tarea.descripcion} (${if (tarea.completada) "Completada" else "Pendiente"})", // Nombre de la tarea y muestra si esta completada o no.
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface,
                                textAlign = TextAlign.Center
                            )
                            IconButton(
                                onClick = {
                                    tasksViewModel.actualizarEstadoTarea(tarea._id) // Cambia el estado de la tarea.
                                    adminViewModel.obtenerUsuariosConTareas() // Actualiza la lista de usuarios y tareas.
                                    actualizar = true
                                },
                                modifier = Modifier.padding(top = 8.dp),
                            ) {
                                Icon(
                                    imageVector = icon, // Ícono que representa el estado de la tarea.
                                    contentDescription = "Eliminar Tarea"
                                )
                            }
                            IconButton(
                                onClick = {
                                    tasksViewModel.eliminarTarea(tarea._id) // Elimina la tarea.
                                    adminViewModel.obtenerUsuariosConTareas() // Actualiza la lista de usuarios y tareas.
                                    actualizar = true
                                },
                                modifier = Modifier.padding(top = 8.dp),
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete, // Ícono para eliminar la tarea.
                                    contentDescription = "Eliminar Tarea"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}