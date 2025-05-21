package com.jluqgon214.hogarmate.components

import androidx.compose.runtime.Composable

/**
 * # Componente de barra inferior personalizada que cambia según el rol del usuario.
 *
 */
@Composable
fun CustomBottomBar(
    isAdmin: Boolean,
    showBottomBar: Boolean,
    selectedBottomBarIndex: Int,
    onItemSelected: (Int) -> Unit,
    onNavigate: (Int) -> Unit,
) {
    if (showBottomBar) {
        if (isAdmin) {
            // Muestra la barra inferior animada para administradores.
            AdminAnimatedBottomBar(
                show = true,
                selectedIndex = selectedBottomBarIndex,
                onItemSelected = onItemSelected,
                onNavigate = onNavigate
            )
        } else {
            // Muestra la barra inferior animada para usuarios regulares.
            AnimatedBottomBar(
                show = true,
                selectedIndex = selectedBottomBarIndex,
                onItemSelected = onItemSelected,
                onNavigate = onNavigate
            )
        }
    }
}