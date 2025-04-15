package com.jluqgon214.hogarmate.model

data class Usuario(
    val _id: String,
    val username: String,
    val password: String,
    val email: String,
    val roles: String,
    val hogar: String? = null
)