package com.jluqgon214.hogarmate.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jluqgon214.hogarmate.components.CustomButton
import com.jluqgon214.hogarmate.components.ThemeSwitcher
import com.jluqgon214.hogarmate.viewModel.AdminViewModel
import com.jluqgon214.hogarmate.viewModel.AppViewModel
import com.jluqgon214.hogarmate.viewModel.ThemeViewModel

@Composable
fun SettingsScreen(themeViewModel: ThemeViewModel, paddingValues: PaddingValues, navController: NavController, appViewModel: AppViewModel, adminViewModel: AdminViewModel) {
    val isDarkTheme = themeViewModel.isDarkTheme.collectAsState().value

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Cambiar el tema:",
                modifier = Modifier.weight(1f)
            )
            ThemeSwitcher(
                darkTheme = isDarkTheme,
                onClick = {
                    themeViewModel.toggleTheme()

                    navController.navigate("SettingsScreen") {
                        popUpTo("SettingsScreen") { inclusive = true }
                    }

                    appViewModel.setSelectedBottomBarIndex(3)

                    adminViewModel.comprobarAdmin()
                },
                size = 60.dp
            )
        }
    }
}