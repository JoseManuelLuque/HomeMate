package com.jluqgon214.hogarmate.screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.getValue
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.jluqgon214.hogarmate.components.CustomButton
import com.jluqgon214.hogarmate.components.EditProfileDialog
import com.jluqgon214.hogarmate.viewModel.AppViewModel
import com.jluqgon214.hogarmate.viewModel.LoginViewModel
import com.jluqgon214.hogarmate.viewModel.ProfileViewModel

@Composable
fun ProfileScreen(navController: NavController, loginViewModel: LoginViewModel, AppViewModel: AppViewModel, profileViewModel: ProfileViewModel) {
    val usuario by profileViewModel.usuario.collectAsState()
    val showEditDialog by profileViewModel.showEditDialog.collectAsState()

    // Cargar la información del usuario al iniciar la pantalla
    LaunchedEffect(Unit) {
        Log.d("ProfileScreen", "Cargando información del usuario")
        profileViewModel.obtenerUsuario()
    }

    AnimatedVisibility(showEditDialog) {
        EditProfileDialog(profileViewModel)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Nombre", fontSize = MaterialTheme.typography.headlineLarge.fontSize)
        Text(usuario?.username ?: "Cargando...", fontSize = MaterialTheme.typography.headlineMedium.fontSize)

        Text("Correo Electrónico", fontSize = MaterialTheme.typography.headlineLarge.fontSize)
        Text(usuario?.email ?: "Cargando...", fontSize = MaterialTheme.typography.headlineMedium.fontSize)

        Row (
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            CustomButton(
                onClick = {
                    profileViewModel.setShowEditDialog(true)
                },
            ) {
                Text("Editar Perfil")
            }

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
                },
            ) {
                Text("Cerrar sesión")
            }
        }
    }
}