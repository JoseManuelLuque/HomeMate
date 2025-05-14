package com.jluqgon214.hogarmate.components

import androidx.compose.runtime.Composable

@Composable
fun PersistentBottomBar(
    isAdmin: Boolean,
    showBottomBar: Boolean,
    selectedBottomBarIndex: Int,
    onItemSelected: (Int) -> Unit,
    onNavigate: (Int) -> Unit,
) {
    if (showBottomBar) {
        if (isAdmin) {
            AdminAnimatedBottomBar(
                show = true,
                selectedIndex = selectedBottomBarIndex,
                onItemSelected = onItemSelected,
                onNavigate = onNavigate
            )
        } else {
            AnimatedBottomBar(
                show = true,
                selectedIndex = selectedBottomBarIndex,
                onItemSelected = onItemSelected,
                onNavigate = onNavigate
            )
        }
    }
}