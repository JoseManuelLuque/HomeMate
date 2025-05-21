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

/**
 * # Pantalla de registro de usuario.
 *
 * Esta pantalla permite a los usuarios registrarse proporcionando un nombre de usuario, correo electrónico
 * y contraseña. También incluye validación de contraseñas y muestra mensajes de error o éxito.
 *
 * @param registerViewModel ViewModel que gestiona la lógica de registro.
 * @param navController Controlador de navegación para gestionar la navegación entre pantallas.
 */
@Composable
fun RegisterScreen(registerViewModel: RegisterViewModel, navController: NavController) {
    // Variables de estado observadas desde el ViewModel.
    val username by registerViewModel.username.collectAsState()
    val email by registerViewModel.email.collectAsState()
    val password by registerViewModel.password.collectAsState()
    val repeatPassword by registerViewModel.passwordRepeat.collectAsState()
    val registerResponse by registerViewModel.registerResponse.collectAsState()
    val errorMessage by registerViewModel.errorMessage.collectAsState()

    // Contenedor principal de la pantalla.
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center, // Centra los elementos verticalmente.
        horizontalAlignment = Alignment.CenterHorizontally // Centra los elementos horizontalmente.
    ) {
        // Muestra un mensaje de éxito si el registro fue exitoso.
        registerResponse?.let {
            Text("Registro successful: ${it.message}")
            navController.navigate("loginScreen")
        }

        // Muestra un mensaje de error si existe.
        errorMessage?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        // Campo de texto para el nombre de usuario.
        CustomTextField(
            value = username,
            onValueChange = { registerViewModel.setUsername(it) },
            label = "Usuario",
            placeholder = "Escribe tu usuario"
        )

        // Campo de texto para el correo electrónico.
        CustomTextField(
            value = email,
            onValueChange = { registerViewModel.setEmail(it) },
            label = "Email",
            placeholder = "Escribe tu Email"
        )

        // Campo de texto para la contraseña.
        CustomTextField(
            value = password,
            onValueChange = { registerViewModel.setPassword(it) },
            label = "Contraseña",
            placeholder = "Escribe tu contraseña"
        )

        // Campo de texto para repetir la contraseña.
        CustomTextField(
            value = repeatPassword,
            onValueChange = { registerViewModel.setRepeatPassword(it) },
            label = "Repite tu Contraseña",
            placeholder = "Escribe tu contraseña de nuevo"
        )

        // Fila con botones para volver y registrarse.
        Row {
            // Botón para volver a la pantalla de inicio de sesión.
            CustomButton(
                content = { Text("Volver") },
                onClick = {
                    navController.navigate("loginScreen")
                }
            )

            // Botón para realizar el registro.
            CustomButton(
                content = { Text("Registrarse") },
                onClick = {
                    registerViewModel.register()
                }
            )
        }
    }
}