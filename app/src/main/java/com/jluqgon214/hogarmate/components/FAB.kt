package com.jluqgon214.hogarmate.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * # Componente de botón de acción flotante (FAB) para agregar tareas.
 *
 * @param onFabClick Acción a ejecutar cuando se hace clic en el FAB (En esta app abrir el dialogo para añadir tareas).
 * @param show Indica si el FAB debe mostrarse o no.
 */
@Composable
fun FAB(onFabClick: () -> Unit, show: Boolean) {
    if (show) {
        // Botón de acción flotante grande con colores personalizados.
        LargeFloatingActionButton(
            onClick = { onFabClick() },
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = Color.Black,
        ) {
            // Icono dentro del FAB con una descripción de contenido.
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Agregar tarea"
            )
        }
    }
}