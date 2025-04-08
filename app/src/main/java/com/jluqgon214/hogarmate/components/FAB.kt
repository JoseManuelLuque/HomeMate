package com.jluqgon214.hogarmate.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.jluqgon214.hogarmate.ui.theme.GreenTertiary

@Composable
fun FAB(onFabClick: () -> Unit, show: Boolean) {
    if (show) {
        LargeFloatingActionButton(
            onClick = { onFabClick() },
            containerColor = GreenTertiary,
            contentColor = Color.Black,
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Agregar tarea"
            )
        }
    }
}