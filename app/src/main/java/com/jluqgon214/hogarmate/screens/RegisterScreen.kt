package com.jluqgon214.hogarmate.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
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
        Spacer(modifier = Modifier.height(16.dp))

        // Campo de texto para el correo electrónico.
        CustomTextField(
            value = email,
            onValueChange = { registerViewModel.setEmail(it) },
            label = "Email",
            placeholder = "Escribe tu Email"
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Campo de texto para la contraseña.
        CustomTextField(
            value = password,
            onValueChange = { registerViewModel.setPassword(it) },
            label = "Contraseña",
            placeholder = "Escribe tu contraseña"
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Campo de texto para repetir la contraseña.
        CustomTextField(
            value = repeatPassword,
            onValueChange = { registerViewModel.setRepeatPassword(it) },
            label = "Repite tu Contraseña",
            placeholder = "Escribe tu contraseña de nuevo"
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Fila con botones para volver y registrarse, separados y con padding horizontal.
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Botón para volver a la pantalla de inicio de sesión.
            CustomButton(
                content = { Text("Volver") },
                onClick = { navController.navigate("loginScreen") }
            )
            Spacer(modifier = Modifier.width(16.dp))
            // Botón para realizar el registro.
            CustomButton(
                content = { Text("Registrarse") },
                onClick = { registerViewModel.register() }
            )
        }
    }
}