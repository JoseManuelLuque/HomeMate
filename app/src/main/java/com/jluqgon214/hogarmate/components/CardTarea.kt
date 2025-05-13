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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

@Composable
fun CardTarea(
    task: Tarea,
    assignedUser: String,
    onComplete: () -> Unit,
    onDelete: () -> Unit
) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    var isSwiped by remember { mutableStateOf(false) }
    val animatedOffsetX by animateFloatAsState(
        targetValue = offsetX,
        animationSpec = tween(durationMillis = 300),
        label = "Swipe Animation"
    )
    val coroutineScope = rememberCoroutineScope()

    if (isSwiped) {
        LaunchedEffect(Unit) {
            delay(300) // Espera la animación antes de eliminar la tarea
            onDelete()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        // 🔴 Fondo rojo con icono de papelera
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red, RoundedCornerShape(16.dp))
                .padding(16.dp),
            contentAlignment = Alignment.CenterEnd // Ubicar el icono a la derecha
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

        // 🟢 Tarjeta de tarea
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = animatedOffsetX.dp) // Aplica la animación de desplazamiento
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onDragEnd = {
                            if (offsetX < -300) { // Si el swipe es lo suficientemente fuerte
                                coroutineScope.launch {
                                    offsetX = -500f // Desliza fuera de la pantalla
                                    isSwiped = true
                                }
                            } else {
                                offsetX = 0f // Si no, regresa a su posición inicial
                            }
                        }
                    ) { _, dragAmount ->
                        if (dragAmount < 0) { // Solo permite swipe a la izquierda
                            offsetX += dragAmount
                        }
                    }
                }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = task.descripcion, fontSize = 18.sp)
                    Text(text = "Asignado a: $assignedUser", fontSize = 14.sp, color = Color.Gray)
                }
                IconButton(onClick = onComplete) {
                    if (task.completada) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Redo,
                            contentDescription = "Completar tarea",
                            tint = Color.Green
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Desacer completar tarea",
                            tint = Color.Green
                        )
                    }

                }
            }
        }
    }
}
