package com.jluqgon214.hogarmate.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jluqgon214.hogarmate.viewModel.ProfileViewModel
/**
 * # Diálogo de edición de perfil.
 *
 * Este componente muestra un cuadro de diálogo que permite al usuario editar su perfil,
 * incluyendo el nombre de usuario y el correo electrónico.
 *
 * @param profileViewModel ViewModel que gestiona el estado y las acciones relacionadas con el perfil del usuario.
 */
@Composable
fun EditProfileDialog(
    profileViewModel: ProfileViewModel,
) {

    // Efecto lanzado al inicializar el componente para establecer los valores iniciales del formulario.
    LaunchedEffect(Unit) {
        profileViewModel.setUsername(profileViewModel.usuario.value?.username ?: "")
        profileViewModel.setEmail(profileViewModel.usuario.value?.email ?: "")
    }

    // Estado actual del nombre de usuario y correo electrónico.
    val username = profileViewModel.username.collectAsState().value
    val email = profileViewModel.email.collectAsState().value

    val errorMessage = profileViewModel.errorMessage.collectAsState().value
    val isFormValid = profileViewModel.isFormValid()

    AlertDialog(
        onDismissRequest = { profileViewModel.setShowEditDialog(false) }, // Acción al cerrar el diálogo.
        title = {
            Text(text = "Editar Perfil")
        },
        text = {
            Column {
                if (errorMessage != null) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }
            Column {
                // Campo de texto para editar el nombre de usuario.
                CustomTextField(
                    label = "Nombre de usuario",
                    value = username,
                    onValueChange = {
                        profileViewModel.setUsername(it)
                    },
                    placeholder = "Nombre de usuario"
                )
                // Campo de texto para editar el correo electrónico.
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
            // Botón para confirmar y guardar los cambios.
            AlertButton(
                onClick = {
                    profileViewModel.actualizarUsuario()
                    profileViewModel.setShowEditDialog(false)
                },
                text = "Guardar",
                textColor = MaterialTheme.colorScheme.primary,
                enabled = isFormValid // Hasta que el formulario sea válido, el botón estará habilitado.
            )
        },
        dismissButton = {
            // Botón para cancelar y cerrar el diálogo.
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
        shape = RoundedCornerShape(16.dp), // Forma redondeada del diálogo.
        tonalElevation = 6.dp // Elevación del diálogo.
    )
}