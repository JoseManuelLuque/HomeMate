package com.jluqgon214.hogarmate.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Redo
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jluqgon214.hogarmate.model.Tarea
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * # Componente que representa una tarjeta de tarea interactiva.
 *
 * Este componente permite mostrar información de una tarea, asignarla a un usuario,
 * completarla o eliminarla mediante gestos de deslizamiento.
 *
 * @param task Objeto que contiene la información de la tarea.
 * @param assignedUser Nombre del usuario asignado a la tarea.
 * @param onComplete Callback que se ejecuta al marcar la tarea como completada o deshacer su completado.
 * @param onDelete Callback que se ejecuta al eliminar la tarea.
 */
@Composable
fun CardTarea(
    task: Tarea,
    assignedUser: String,
    onComplete: () -> Unit,
    onDelete: () -> Unit
) {
    // Estado que controla el desplazamiento horizontal de la tarjeta.
    var offsetX by remember { mutableFloatStateOf(0f) }
    // Estado que indica si la tarjeta ha sido deslizada completamente.
    var isSwiped by remember { mutableStateOf(false) }
    // Animación para el desplazamiento horizontal.
    val animatedOffsetX by animateFloatAsState(
        targetValue = offsetX,
        animationSpec = tween(durationMillis = 300),
        label = "Swipe Animation"
    )
    // Alcance de la corrutina para manejar acciones asincrónicas.
    val coroutineScope = rememberCoroutineScope()

    // Efecto lanzado al deslizar completamente la tarjeta para eliminarla.
    if (isSwiped) {
        LaunchedEffect(Unit) {
            delay(300) // Espera la animación antes de ejecutar la acción de eliminación.
            onDelete()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        // Fondo rojo con ícono de papelera, visible al deslizar la tarjeta.
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.Red,
                    RoundedCornerShape(16.dp)
                ) // Fondo rojo con bordes redondeados.
                .padding(16.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Eliminar",
                tint = Color.White,
                modifier = Modifier
                    .size(32.dp)
                    .padding(end = 16.dp)
            )
        }

        // Tarjeta principal que muestra la información de la tarea.
        Card(
            colors = CardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onSurface,
                disabledContentColor = MaterialTheme.colorScheme.onSurface,
                disabledContainerColor = MaterialTheme.colorScheme.surface
            ),
            shape = RoundedCornerShape(16.dp), // Bordes redondeados de la tarjeta.
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp), // Elevación de la tarjeta.
            modifier = Modifier
                .fillMaxWidth() // La tarjeta ocupa todo el ancho disponible.
                .offset(x = animatedOffsetX.dp) // Aplica la animación de desplazamiento.
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onDragEnd = {
                            if (offsetX < -300) { // Si el deslizamiento es lo suficientemente fuerte.
                                coroutineScope.launch {
                                    offsetX = -500f // Desliza fuera de la pantalla.
                                    isSwiped = true // Marca la tarjeta como deslizada.
                                }
                            } else {
                                offsetX = 0f // Si no, regresa a su posición inicial.
                            }
                        }
                    ) { _, dragAmount ->
                        if (dragAmount < 0) { // Solo permite deslizamiento hacia la izquierda.
                            offsetX += dragAmount
                        }
                    }
                }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween // Distribuye los elementos horizontalmente.
            ) {
                Column {
                    Text(
                        text = task.descripcion, // Nombre de la tarea.
                        fontSize = 18.sp
                    )
                    Text(
                        text = "Asignado a: $assignedUser", // Usuario asignado.
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
                IconButton(onClick = onComplete) {
                    if (task.completada) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Redo,
                            contentDescription = "Completar tarea"
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Deshacer completar tarea"
                        )
                    }
                }
            }
        }
    }
}