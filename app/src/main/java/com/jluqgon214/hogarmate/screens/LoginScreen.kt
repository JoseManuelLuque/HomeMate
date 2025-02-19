package com.jluqgon214.hogarmate.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jluqgon214.hogarmate.components.CustomButton
import com.jluqgon214.hogarmate.components.CustomTextField
import com.jluqgon214.hogarmate.viewModel.LoginViewModel

@Composable
fun LoginScreen() {
    val viewModel: LoginViewModel = LoginViewModel()

    val username by viewModel.username.collectAsState()
    val password by viewModel.password.collectAsState()
    val loginResponse by viewModel.loginResponse.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        CustomTextField(
            value = username,
            onValueChange = { viewModel.setUsername(it) },
            label = "Usuario",
            placeholder = "Escribe tu usuario"
        )
        CustomTextField(
            value = password,
            onValueChange = { viewModel.setPassword(it) },
            label = "Contraseña",
            placeholder = "Escribe tu contraseña"
        )
        CustomButton(onClick = { viewModel.login() }, text = "Ingresar")
        loginResponse?.let {
            Text("Login successful: ${it.token}")
        }
        errorMessage?.let {
            Text("Error: $it")
        }
    }
}