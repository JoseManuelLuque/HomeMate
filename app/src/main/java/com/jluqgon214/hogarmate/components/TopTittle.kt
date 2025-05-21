package com.jluqgon214.hogarmate.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

/**
 * # Componente de encabezado superior que muestra un título centrado.
 *
 * @param texto El texto que se mostrará como título.
 */
@Composable
fun TopTittle(texto: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth() // Ocupa todo el ancho disponible.
            .background(MaterialTheme.colorScheme.primaryContainer) // Aplica un color de fondo basado en el tema.
            .systemBarsPadding(), // Agrega un relleno para evitar superposición con las barras del sistema.
    ) {
        Text(
            texto, // Texto a mostrar.
            textAlign = TextAlign.Center, // Alinea el texto al centro.
            modifier = Modifier.fillMaxWidth(), // El texto ocupa todo el ancho disponible.
            style = MaterialTheme.typography.titleLarge // Aplica un estilo de texto grande basado en el tema.
        )
    }
}