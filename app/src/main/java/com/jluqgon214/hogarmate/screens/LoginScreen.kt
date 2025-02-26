package com.jluqgon214.hogarmate.screens

import android.util.Log
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jluqgon214.hogarmate.components.CustomButton
import com.jluqgon214.hogarmate.components.CustomTextField
import com.jluqgon214.hogarmate.ui.theme.GreenPrimary
import com.jluqgon214.hogarmate.viewModel.LoginViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel, navController: NavController) {
    val username by viewModel.username.collectAsState()
    val password by viewModel.password.collectAsState()
    val loginResponse by viewModel.loginResponse.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(GreenPrimary)
            .systemBarsPadding(),
    ) {
        Text("Iniciar Sesión", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), style = MaterialTheme.typography.titleLarge)
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
            value = password,
            onValueChange = { viewModel.setPassword(it) },
            label = "Contraseña",
            placeholder = "Escribe tu contraseña"
        )
        Row() {
            CustomButton(
                text = "Iniciar sesión",
                onClick = {
                    viewModel.login()
                    Log.d("LoginScreen", "Login button clicked")
                    Log.d("Username", username)
                    Log.d("Password", password)
                    Log.d("LoginResponse", loginResponse.toString())
                    Log.d("Error", errorMessage.toString())
                }
            )
            CustomButton(
                text = "Registrarse",
                onClick = {
                    navController.navigate("registerScreen")
                    Log.d("LoginScreen", "Register button clicked")
                    Log.d("Username", username)
                    Log.d("Password", password)
                    Log.d("LoginResponse", loginResponse.toString())
                    Log.d("Error", errorMessage.toString())
                }
            )
        }
        loginResponse?.let {
            Text("Login successful: ${it.token}")
        }
        errorMessage?.let {
            Text("Error: $it")
        }
    }
}