package com.jluqgon214.hogarmate.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

/**
 * # Pantalla principal de la aplicación.
 *
 * Esta pantalla muestra un diseño centrado con un texto de marcador de posición.
 * Es un componente en desarrollo (Work In Progress - WIP).
 */
@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize(), // Ocupa todo el tamaño disponible.
        horizontalAlignment = Alignment.CenterHorizontally, // Alinea los elementos horizontalmente al centro.
        verticalArrangement = Arrangement.Center // Alinea los elementos verticalmente al centro.
    ) {
        Text(
            "WIP",
            fontSize = 200.sp
        )
    }
}