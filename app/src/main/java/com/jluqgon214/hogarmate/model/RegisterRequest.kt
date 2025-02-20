package com.jluqgon214.hogarmate.model

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String,
    val passwordRepeat: String,
    val rol: String? = "USER",
    val hogar: String? = null,
)