package com.jluqgon214.hogarmate.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jluqgon214.hogarmate.components.CustomButton
import com.jluqgon214.hogarmate.components.CustomTextField
import com.jluqgon214.hogarmate.ui.theme.GreenPrimary
import com.jluqgon214.hogarmate.viewModel.RegisterViewModel

@Composable
fun RegisterScreen(viewModel: RegisterViewModel, navController: NavController) {
    val username by viewModel.username.collectAsState()
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val repeatPassword by viewModel.passwordRepeat.collectAsState()
    val registerResponse by viewModel.registerResponse.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(GreenPrimary)
            .systemBarsPadding(),
    ) {
        Text("Registrarse", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), style = MaterialTheme.typography.titleLarge)
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTextField(
            value = username,
            onValueChange = { viewModel.setUsername(it) },
            label = "Usuario",
            placeholder = "Escribe tu usuario"
        )
        CustomTextField(
            value = email,
            onValueChange = { viewModel.setEmail(it) },
            label = "Email",
            placeholder = "Escribe tu Email"
        )
        CustomTextField(
            value = password,
            onValueChange = { viewModel.setPassword(it) },
            label = "Contraseña",
            placeholder = "Escribe tu contraseña"
        )
        CustomTextField(
            value = repeatPassword,
            onValueChange = { viewModel.setRepeatPassword(it) },
            label = "Repite tu Contraseña",
            placeholder = "Escribe tu contraseña de nuevo"
        )
        Row() {
            CustomButton(
                text = "Volver",
                onClick = {
                    navController.navigate("loginScreen")
                }
            )
            CustomButton(
                text = "Registrarse",
                onClick = {
                    viewModel.register()
                }
            )
        }
        registerResponse?.let {
            Text("Registro successful: ${it.message}")
            navController.navigate("loginScreen")
        }
        errorMessage?.let {
            Text("Error: $it, mensaje: ${registerResponse?.message}")
        }
    }

}