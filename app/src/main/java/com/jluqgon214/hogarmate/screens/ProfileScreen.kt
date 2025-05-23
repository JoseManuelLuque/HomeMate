package com.jluqgon214.hogarmate.screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
 * Muestra la información del perfil del usuario, permitiendo editar o cerrar sesión.
 *
 * @param navController Controlador de navegación.
 * @param loginViewModel ViewModel de inicio de sesión.
 * @param AppViewModel ViewModel global de la app.
 * @param profileViewModel ViewModel del perfil de usuario.
 */
@Composable
fun ProfileScreen(
    navController: NavController,
    loginViewModel: LoginViewModel,
    AppViewModel: AppViewModel,
    profileViewModel: ProfileViewModel
) {
    // Estado con la información del usuario.
    val usuario by profileViewModel.usuario.collectAsState()
    // Estado para mostrar el diálogo de edición.
    val showEditDialog by profileViewModel.showEditDialog.collectAsState()

    // Carga la información del usuario al iniciar la pantalla.
    LaunchedEffect(Unit) {
        Log.d("ProfileScreen", "Cargando información del usuario")
        profileViewModel.obtenerUsuario()
    }

    // Diálogo de edición de perfil.
    AnimatedVisibility(showEditDialog) {
        EditProfileDialog(profileViewModel)
    }

    // Contenedor principal con distribución uniforme.
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        // Sección: Nombre de usuario.
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Nombre", fontSize = MaterialTheme.typography.headlineLarge.fontSize)
            Text(
                usuario?.username ?: "Cargando...",
                fontSize = MaterialTheme.typography.headlineSmall.fontSize
            )
        }

        // Sección: Correo electrónico.
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Correo Electrónico", fontSize = MaterialTheme.typography.headlineLarge.fontSize)
            Text(
                usuario?.email ?: "Cargando...",
                fontSize = MaterialTheme.typography.headlineSmall.fontSize
            )
        }

        // Fila de botones: Editar perfil y cerrar sesión.
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Botón: Editar perfil.
            CustomButton(
                onClick = { profileViewModel.setShowEditDialog(true) }
            ) {
                Text("Editar Perfil")
            }
            // Botón: Cerrar sesión.
            CustomButton(
                onClick = {
                    navController.navigate("loginScreen") {
                        popUpTo("loginScreen") { inclusive = true }
                    }
                    loginViewModel.setJWT(null)
                    loginViewModel.setLoginCorrecto(false)
                    loginViewModel.setErrorMessage(null)
                    loginViewModel.setEmail("")
                    loginViewModel.setPassword("")
                    AppViewModel.setSelectedBottomBarIndex(0)
                }
            ) {
                Text("Cerrar sesión")
            }
        }
    }
}