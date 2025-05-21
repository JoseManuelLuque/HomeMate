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
import com.jluqgon214.hogarmate.viewModel.ThemeViewModel

/**
 * # Actividad principal de la aplicación HogarMate.
 *
 * Esta clase extiende `ComponentActivity` y es el punto de entrada de la aplicación.
 * Configura el tema dinámico y la navegación de la aplicación.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Habilita el diseño de borde a borde en la actividad (Sirve para que las barras de estado de andrioid no se solapen con la aplicación).
        enableEdgeToEdge()

        setContent {
            // Instancia del ThemeViewModel para gestionar el tema dinámico.
            val themeViewModel: ThemeViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    /**
                     * Crea una instancia de `ThemeViewModel` utilizando el contexto de la aplicación.
                     *
                     * @param modelClass Clase del ViewModel que se desea crear.
                     * @return Instancia de `ThemeViewModel`.
                     */
                    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                        return ThemeViewModel(applicationContext) as T
                    }
                }
            )

            // Observa el estado del tema (oscuro o claro) desde el ThemeViewModel.
            val isDarkTheme by themeViewModel.isDarkTheme.collectAsState()

            // Aplica el tema dinámico a la interfaz de usuario.
            HogarMateTheme(darkTheme = isDarkTheme) {
                // Configura la navegación de la aplicación.
                AppNavigation(themeViewModel)
            }
        }
    }
}