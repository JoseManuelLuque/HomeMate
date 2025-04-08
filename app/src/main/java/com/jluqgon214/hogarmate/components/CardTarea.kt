package com.jluqgon214.hogarmate.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CardTarea(
    taskName: String,
    assignedUser: String,
    onComplete: () -> Unit,
    onDelete: () -> Unit
) {
    var offsetX by remember { mutableStateOf(0f) }
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
                    Text(text = taskName, fontSize = 18.sp, color = Color.Black)
                    Text(text = "Asignado a: $assignedUser", fontSize = 14.sp, color = Color.Gray)
                }
                IconButton(onClick = onComplete) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Completar tarea",
                        tint = Color.Green
                    )
                }
            }
        }
    }
}
