package com.jluqgon214.hogarmate.screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jluqgon214.hogarmate.components.CustomButton
import com.jluqgon214.hogarmate.components.EditProfileDialog
import com.jluqgon214.hogarmate.viewModel.AppViewModel
import com.jluqgon214.hogarmate.viewModel.LoginViewModel
import com.jluqgon214.hogarmate.viewModel.ProfileViewModel

/**
 * # Pantalla de perfil del usuario.
 *
 * Esta pantalla muestra la información del perfil del usuario, como su nombre y correo electrónico.
 * También permite al usuario editar su perfil o cerrar sesión.
 *
 * @param navController Controlador de navegación para gestionar la navegación entre pantallas.
 * @param loginViewModel ViewModel que gestiona la lógica de inicio de sesión.
 * @param AppViewModel ViewModel que gestiona el estado global de la aplicación.
 * @param profileViewModel ViewModel que gestiona la lógica del perfil del usuario.
 */
@Composable
fun ProfileScreen(
    navController: NavController,
    loginViewModel: LoginViewModel,
    AppViewModel: AppViewModel,
    profileViewModel: ProfileViewModel
) {
    // Estado que contiene la información del usuario.
    val usuario by profileViewModel.usuario.collectAsState()

    // Estado que indica si el diálogo de edición de perfil está visible.
    val showEditDialog by profileViewModel.showEditDialog.collectAsState()

    // Cargar la información del usuario al iniciar la pantalla.
    LaunchedEffect(Unit) {
        Log.d("ProfileScreen", "Cargando información del usuario")
        profileViewModel.obtenerUsuario()
    }

    // Muestra el diálogo de edición de perfil si está activo.
    AnimatedVisibility(showEditDialog) {
        EditProfileDialog(profileViewModel)
    }

    // Contenedor principal de la pantalla.
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Muestra el nombre del usuario.
        Text("Nombre", fontSize = MaterialTheme.typography.headlineLarge.fontSize)
        Text(
            usuario?.username ?: "Cargando...",
            fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )

        // Muestra el correo electrónico del usuario.
        Text("Correo Electrónico", fontSize = MaterialTheme.typography.headlineLarge.fontSize)
        Text(
            usuario?.email ?: "Cargando...",
            fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )

        // Fila con botones para editar el perfil y cerrar sesión.
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            // Botón para abrir el diálogo de edición de perfil.
            CustomButton(
                onClick = {
                    profileViewModel.setShowEditDialog(true)
                },
            ) {
                Text("Editar Perfil")
            }

            // Botón para cerrar sesión.
            CustomButton(
                onClick = {
                    navController.navigate("loginScreen") {
                        popUpTo("loginScreen") { inclusive = true }
                    }
                    // Resetear el estado de inicio de sesión.
                    loginViewModel.setJWT(null)
                    loginViewModel.setLoginCorrecto(false)
                    loginViewModel.setErrorMessage(null)
                    loginViewModel.setEmail("")
                    loginViewModel.setPassword("")

                    AppViewModel.setSelectedBottomBarIndex(0)
                },
            ) {
                Text("Cerrar sesión")
            }
        }
    }
}