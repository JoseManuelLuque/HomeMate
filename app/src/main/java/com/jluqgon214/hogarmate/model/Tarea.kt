package com.jluqgon214.hogarmate.model

data class Tarea(
    val id: String? = null,
    val descripcion: String,
    val completada: Boolean,
    val usuario: Usuario
)