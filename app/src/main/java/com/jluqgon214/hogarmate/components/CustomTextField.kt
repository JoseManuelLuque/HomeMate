package com.jluqgon214.hogarmate.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/**
 * # Componente de campo de texto personalizado.
 *
 * @param label Etiqueta que se mostrará encima del campo de texto.
 * @param value Valor actual del campo de texto.
 * @param onValueChange Callback que se ejecuta cuando el valor del campo cambia, recibe el nuevo valor como argumento.
 * @param placeholder Texto de marcador de posición que se muestra cuando el campo está vacío.
 */
@Composable
fun CustomTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    OutlinedTextField(
        value = value, // Valor actual del campo de texto.
        onValueChange = { onValueChange(it) }, // Maneja los cambios en el valor del campo.
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        singleLine = true, // Indica que el campo es de una sola línea.
        maxLines = 1, // Número máximo de líneas permitidas.
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary, // Color del borde cuando el campo está enfocado.
            unfocusedBorderColor = MaterialTheme.colorScheme.tertiary, // Color del borde cuando el campo no está enfocado.
            cursorColor = MaterialTheme.colorScheme.primary, // Color del cursor.
            focusedLabelColor = MaterialTheme.colorScheme.secondary, // Color de la etiqueta cuando el campo está enfocado.
        )
    )
}