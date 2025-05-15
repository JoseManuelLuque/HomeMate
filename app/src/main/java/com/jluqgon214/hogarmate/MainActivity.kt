package com.jluqgon214.hogarmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jluqgon214.hogarmate.navigation.AppNavigation
import com.jluqgon214.hogarmate.ui.theme.HogarMateTheme
import com.jluqgon214.hogarmate.viewModel.AdminViewModel
import com.jluqgon214.hogarmate.viewModel.ThemeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Instancia del ThemeViewModel
            val themeViewModel: ThemeViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                        return ThemeViewModel(applicationContext) as T
                    }
                }
            )

            val isDarkTheme by themeViewModel.isDarkTheme.collectAsState()

            // Aplicar el tema dinámico
            HogarMateTheme(darkTheme = isDarkTheme) {
                AppNavigation(themeViewModel)
            }
        }
    }
}