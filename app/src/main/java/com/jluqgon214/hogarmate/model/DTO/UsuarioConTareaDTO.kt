package com.jluqgon214.hogarmate.model.DTO

// UsuarioConTareas.kt
data class UsuarioConTareasDTO(
    val _id: String?,
    val username: String,
    val email: String,
    val roles: String,
    val tareas: List<TareaDTO>
)