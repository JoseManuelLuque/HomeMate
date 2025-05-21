package com.jluqgon214.hogarmate.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Straight
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.utils.noRippleClickable

/**
 * # Componente de barra de navegación inferior animada paraa un usuario normal que no es administrador.
 *
 * @param show Indica si la barra de navegación debe mostrarse.
 * @param selectedIndex Índice del elemento actualmente seleccionado.
 * @param onItemSelected Callback que se ejecuta cuando se selecciona un elemento, recibe el índice del elemento.
 * @param onNavigate Callback que se ejecuta para manejar la navegación, recibe el índice del elemento seleccionado.
 */
@Composable
fun AnimatedBottomBar(
    show: Boolean,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    onNavigate: (Int) -> Unit
) {
    // Lista de elementos de la barra de navegación, recordada para evitar recomposiciones innecesarias.
    val navigationBarItems = remember { NavegationBarItems.entries }

    if (show) {
        AnimatedNavigationBar(
            modifier = Modifier.height(62.dp), // Altura de la barra de navegación.
            selectedIndex = selectedIndex, // Índice del elemento seleccionado.
            ballColor = MaterialTheme.colorScheme.primary, // Color de la bola animada.
            barColor = Color.Black, // Color de fondo de la barra.
            ballAnimation = Straight(tween(300)), // Animación de la bola al cambiar de elemento.
            indentAnimation = Height(tween(300)), // Animación de indentación al cambiar de elemento.
        ) {
            navigationBarItems.forEach { item ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .noRippleClickable {
                            onItemSelected(item.ordinal) // Maneja la selección del elemento.
                            onNavigate(item.ordinal) // Maneja la navegación al elemento seleccionado.
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(26.dp),
                        imageVector = item.icon,
                        contentDescription = "Icono Bottom Bar",
                        tint = if (selectedIndex == item.ordinal)
                            MaterialTheme.colorScheme.primary
                        else
                            Color.Gray
                    )
                }
            }
        }
    }
}

/**
 * # Enum class que define los elementos de la barra de navegación.
 *
 * @property icon Ícono asociado al elemento de la barra de navegación.
 */
enum class NavegationBarItems(val icon: ImageVector) {
    Home(Icons.Filled.Home), // Elemento "Home" con su ícono.
    Tasks(Icons.Filled.Check), // Elemento "Tasks" con su ícono.
    Profile(Icons.Filled.Person), // Elemento "Profile" con su ícono.
    Settings(Icons.Filled.Settings) // Elemento "Settings" con su ícono.
}