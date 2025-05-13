package com.jluqgon214.hogarmate.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jluqgon214.hogarmate.ui.theme.GreenPrimary
import com.jluqgon214.hogarmate.viewModel.ProfileViewModel

@Composable
fun EditProfileDialog(
    profileViewModel: ProfileViewModel,
) {

    LaunchedEffect(Unit){
        profileViewModel.setUsername(profileViewModel.usuario.value?.username ?: "")
        profileViewModel.setEmail(profileViewModel.usuario.value?.email ?: "")
    }

    val username = profileViewModel.username.collectAsState().value
    val email = profileViewModel.email.collectAsState().value


    AlertDialog(
        onDismissRequest = { profileViewModel.setShowEditDialog(false) },
        title = {
            Text(text = "Editar Perfil")
        },
        text = {
            Column {
                CustomTextField(
                    label = "Nombre de usuario",
                    value = username,
                    onValueChange = {
                        profileViewModel.setUsername(it)
                    },
                    placeholder = "Nombre de usuario"
                )
                CustomTextField(
                    label = "Correo Electrónico",
                    value = email,
                    onValueChange = {
                        profileViewModel.setEmail(it)
                    },
                    placeholder = "Correo Electrónico"
                )
            }
        },
        confirmButton = {
            AlertButton(
                onClick = {
                    profileViewModel.actualizarUsuario()
                    profileViewModel.setShowEditDialog(false)
                },
                text = "Guardar",
                textColor = GreenPrimary,
            )
        },
        dismissButton = {
            AlertButton(
                onClick = {
                    profileViewModel.setShowEditDialog(false)
                },
                text = "Cancelar",
                textColor = Color.Red
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        tonalElevation = 6.dp
    )
}