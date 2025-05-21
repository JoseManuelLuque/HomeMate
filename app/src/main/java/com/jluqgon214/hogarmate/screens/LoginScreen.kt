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

/**
 * # Pantalla de inicio de sesión.
 *
 * Esta pantalla permite al usuario iniciar sesión proporcionando su correo electrónico y contraseña.
 * También incluye un botón para navegar a la pantalla de registro.
 *
 * @param loginViewModel ViewModel que gestiona la lógica de inicio de sesión.
 * @param navController Controlador de navegación para gestionar la navegación entre pantallas.
 */
@Composable
fun LoginScreen(loginViewModel: LoginViewModel, navController: NavController) {
    // Variables de estado observadas desde el ViewModel.
    val email by loginViewModel.email.collectAsState()
    val password by loginViewModel.password.collectAsState()
    val errorMessage by loginViewModel.errorMessage.collectAsState()
    val loginCorrecto by loginViewModel.loginCorrecto.collectAsState()
    val isLoading by loginViewModel.isLoading.collectAsState()

    // Contenedor principal de la pantalla.
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center, // Centra los elementos verticalmente.
        horizontalAlignment = Alignment.CenterHorizontally // Centra los elementos horizontalmente.
    ) {
        // Muestra un mensaje de error si existe.
        errorMessage?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        // Campo de texto para el correo electrónico.
        CustomTextField(
            value = email,
            onValueChange = { loginViewModel.setEmail(it) },
            label = "Correo Electrónico",
            placeholder = "Escribe tu correo"
        )

        // Campo de texto para la contraseña.
        CustomTextField(
            value = password,
            onValueChange = { loginViewModel.setPassword(it) },
            label = "Contraseña",
            placeholder = "Escribe tu contraseña"
        )

        // Fila con botones para iniciar sesión y registrarse.
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween // Espacia los botones uniformemente.
        ) {
            // Botón para iniciar sesión.
            CustomButton(
                onClick = {
                    // Resetea el mensaje de error antes de intentar iniciar sesión.
                    loginViewModel.setErrorMessage(null)
                    loginViewModel.login()
                },
                content = {
                    if (isLoading) {
                        // Muestra un indicador de carga si el inicio de sesión está en progreso.
                        CircularProgressIndicator(
                            modifier = Modifier.testTag("CircularProgressIndicator"),
                            color = Color.White
                        )
                    } else {
                        Text("Iniciar sesión")
                    }
                }
            )

            // Botón para navegar a la pantalla de registro.
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

    // Efecto lanzado para observar cambios en el estado de inicio de sesión.
    LaunchedEffect(loginCorrecto) {
        if (loginCorrecto) {
            // Guarda el token JWT para su uso posterior.
            loginViewModel.setJWT(loginViewModel.loginResponse.value?.token)

            // Limpia el historial de navegación para evitar volver a la pantalla de inicio de sesión.
            navController.popBackStack()

            // Navega a la pantalla principal.
            navController.navigate("homeScreen")
        }
    }
}