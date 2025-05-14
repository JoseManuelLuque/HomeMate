package com.jluqgon214.hogarmate.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jluqgon214.hogarmate.components.CustomButton
import com.jluqgon214.hogarmate.components.CustomTextField
import com.jluqgon214.hogarmate.viewModel.LoginViewModel

@Composable
fun LoginScreen(loginViewModel: LoginViewModel, navController: NavController) {
    val email by loginViewModel.email.collectAsState()
    val password by loginViewModel.password.collectAsState()
    val errorMessage by loginViewModel.errorMessage.collectAsState()
    val loginCorrecto by loginViewModel.loginCorrecto.collectAsState()
    val isLoading by loginViewModel.isLoading.collectAsState()
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        errorMessage?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }
        CustomTextField(
            value = email,
            onValueChange = { loginViewModel.setEmail(it) },
            label = "Correo Electrónico",
            placeholder = "Escribe tu correo"
        )
        CustomTextField(
            value = password,
            onValueChange = { loginViewModel.setPassword(it) },
            label = "Contraseña",
            placeholder = "Escribe tu contraseña"
        )
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CustomButton(
                onClick = {
                    // Reseteamos el mensaje de error para que se actualice
                    loginViewModel.setErrorMessage(null)

                    loginViewModel.login()
                },
                content = {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.testTag("CircularProgressIndicator"),
                            color = Color.White
                        )
                    } else {
                        Text("Iniciar sesión")
                    }
                }
            )
            CustomButton(
                onClick = {
                    navController.navigate("registerScreen")
                },
                content = {
                    Text("Registrarse")
                }
            )
        }
    }

    // Observa los cambios en loginCorrecto y navega si es true
    LaunchedEffect(loginCorrecto) {
        if (loginCorrecto) {
            // Guardamos el JWT en una variable para usarlo más tarde
            loginViewModel.setJWT(loginViewModel.loginResponse.value?.token)

            // Borrar el trazo de navegación para que no se pueda volver acceder a la pantalla de inicio de sesión a no ser que se cierre la sesión
            navController.popBackStack()

            // Navegar a la pantalla de inicio
            navController.navigate("homeScreen")
        }
    }
}