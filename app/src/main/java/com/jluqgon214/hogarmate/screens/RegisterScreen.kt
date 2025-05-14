package com.jluqgon214.hogarmate.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jluqgon214.hogarmate.components.CustomButton
import com.jluqgon214.hogarmate.components.CustomTextField
import com.jluqgon214.hogarmate.viewModel.RegisterViewModel

@Composable
fun RegisterScreen(registerViewModel: RegisterViewModel, navController: NavController) {
    val username by registerViewModel.username.collectAsState()
    val email by registerViewModel.email.collectAsState()
    val password by registerViewModel.password.collectAsState()
    val repeatPassword by registerViewModel.passwordRepeat.collectAsState()
    val registerResponse by registerViewModel.registerResponse.collectAsState()
    val errorMessage by registerViewModel.errorMessage.collectAsState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        registerResponse?.let {
            Text("Registro successful: ${it.message}")
            navController.navigate("loginScreen")
        }
        errorMessage?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }
        CustomTextField(
            value = username,
            onValueChange = { registerViewModel.setUsername(it) },
            label = "Usuario",
            placeholder = "Escribe tu usuario"
        )
        CustomTextField(
            value = email,
            onValueChange = { registerViewModel.setEmail(it) },
            label = "Email",
            placeholder = "Escribe tu Email"
        )
        CustomTextField(
            value = password,
            onValueChange = { registerViewModel.setPassword(it) },
            label = "Contraseña",
            placeholder = "Escribe tu contraseña"
        )
        CustomTextField(
            value = repeatPassword,
            onValueChange = { registerViewModel.setRepeatPassword(it) },
            label = "Repite tu Contraseña",
            placeholder = "Escribe tu contraseña de nuevo"
        )
        Row {
            CustomButton(
                content = { Text("Volver") },
                onClick = {
                    navController.navigate("loginScreen")
                }
            )
            CustomButton(
                content = { Text("Registrarse") },
                onClick = {
                    registerViewModel.register()
                }
            )
        }
    }

}