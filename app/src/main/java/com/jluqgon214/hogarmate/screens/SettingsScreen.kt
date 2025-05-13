package com.jluqgon214.hogarmate.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jluqgon214.hogarmate.components.ThemeSwitcher
import com.jluqgon214.hogarmate.viewModel.ThemeViewModel

@Composable
fun SettingsScreen(themeViewModel: ThemeViewModel, paddingValues: PaddingValues) {
    val isDarkTheme = themeViewModel.isDarkTheme.collectAsState().value

    Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
        ThemeSwitcher(
            darkTheme = isDarkTheme,
            onClick = {
                themeViewModel.toggleTheme()
            }
        )
    }
}