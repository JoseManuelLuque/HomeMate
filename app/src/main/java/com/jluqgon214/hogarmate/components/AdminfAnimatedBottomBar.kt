package com.jluqgon214.hogarmate.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
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
 * # Composable que muestra una barra de navegación inferior animada para el usuario que es administrador.
 *
 * @param show Indica si la barra debe mostrarse o no.
 * @param selectedIndex Índice del elemento actualmente seleccionado.
 * @param onItemSelected Callback que se ejecuta cuando se selecciona un elemento, recibe el índice del elemento.
 * @param onNavigate Callback que se ejecuta para navegar al elemento seleccionado, recibe el índice del elemento.
 */
@Composable
fun AdminAnimatedBottomBar(
    show: Boolean,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    onNavigate: (Int) -> Unit
) {
    val navigationBarItems = remember { AdminNavegationBarItems.entries }

    if (show) {
        AnimatedNavigationBar(
            modifier = Modifier.height(62.dp),
            selectedIndex = selectedIndex,
            ballColor = MaterialTheme.colorScheme.primary,
            barColor = Color.Black,
            ballAnimation = Straight(tween(300)),
            indentAnimation = Height(tween(300)),
        ) {
            navigationBarItems.forEach { item ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .noRippleClickable {
                            onItemSelected(item.ordinal)
                            onNavigate(item.ordinal)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(26.dp),
                        imageVector = item.icon,
                        contentDescription = "Icono Bottom Bar",
                        tint = if (selectedIndex == item.ordinal) MaterialTheme.colorScheme.primary else Color.Gray
                    )
                }
            }
        }
    }
}

/**
 * Enum class que representa los elementos de la barra de navegación inferior
 *
 * @param icon El icono que se mostrará para cada elemento
 */
enum class AdminNavegationBarItems(val icon: ImageVector) {
    Home(Icons.Filled.Home),
    Tasks(Icons.Filled.Check),
    Profile(Icons.Filled.Person),
    Settings(Icons.Filled.Settings),
    Admin(Icons.Filled.Build)
}