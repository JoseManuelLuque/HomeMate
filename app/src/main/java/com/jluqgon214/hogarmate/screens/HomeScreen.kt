package com.jluqgon214.hogarmate.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jluqgon214.hogarmate.components.CardTarea
import com.jluqgon214.hogarmate.viewModel.HomeViewModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel, paddingValues: PaddingValues) {
    val tareas by homeViewModel.tareas.collectAsState()
    val tareasCargando by homeViewModel.tareasCargando.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModel.obtenerTareas()
    }



    if (tareasCargando) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            for (tarea in tareas) {
                item {
                    CardTarea(tarea.descripcion, "Usuario", { TODO() }, { TODO() })
                }
            }
        }
    }
}