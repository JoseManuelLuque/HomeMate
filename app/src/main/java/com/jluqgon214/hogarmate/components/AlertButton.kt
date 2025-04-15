package com.jluqgon214.hogarmate.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun AlertButton(
    onClick: () -> Unit,
    text: String,
    textColor: Color,
    enabled: Boolean = true // Agregar el parámetro enabled con un valor predeterminado
) {
    TextButton(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = textColor,
            disabledContainerColor = Color.Transparent
        )
    ) {
        Text(text = text)
    }
}