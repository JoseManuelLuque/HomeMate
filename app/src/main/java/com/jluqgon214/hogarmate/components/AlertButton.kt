package com.jluqgon214.hogarmate.components

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Componente de botón de alerta personalizado.
 *
 * @param onClick Acción a ejecutar cuando se presiona el botón.
 * @param text Texto que se mostrará en el botón.
 * @param textColor Color del texto del botón.
 * @param enabled Indica si el botón está habilitado o deshabilitado (por defecto es true).
 */
@Composable
fun AlertButton(
    onClick: () -> Unit,
    text: String,
    textColor: Color,
    enabled: Boolean = true
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