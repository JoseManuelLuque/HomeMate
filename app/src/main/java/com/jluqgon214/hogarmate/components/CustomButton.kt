package com.jluqgon214.hogarmate.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * # Componente de botón personalizado.
 *
 * @param onClick Acción a ejecutar cuando se presiona el botón.
 * @param content Contenido composable que se mostrará dentro del botón.
 */
@Composable
fun CustomButton(onClick: () -> Unit, content: @Composable () -> Unit) {
    Button(
        modifier = Modifier.size(150.dp, 60.dp), // Tamaño del botón (ancho x alto).
        onClick = { onClick() }, // Acción a ejecutar al hacer clic.
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer, // Color de fondo del botón.
            contentColor = MaterialTheme.colorScheme.inverseSurface // Color del contenido del botón.
        )
    ) {
        content() // Renderiza el contenido proporcionado dentro del botón.
    }
}