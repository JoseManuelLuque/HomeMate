package com.jluqgon214.hogarmate.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jluqgon214.hogarmate.preferences.ThemePreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * # ViewModel para gestionar el estado del tema de la aplicación (modo claro/oscuro).
 *
 * @constructor Crea una instancia de ThemeViewModel con el contexto proporcionado.
 * @param context El contexto de la aplicación necesario para acceder a las preferencias.
 */
class ThemeViewModel(context: Context) : ViewModel() {
    /**
     * Instancia de `ThemePreferences` para gestionar las preferencias del tema.
     */
    private val themePreferences = ThemePreferences(context)

    /**
     * Flujo mutable que almacena el estado actual del tema oscuro.
     */
    private val _isDarkTheme = MutableStateFlow(false)

    /**
     * Flujo de estado que expone si el tema oscuro está activado.
     */
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme

    /**
     * Inicializa el estado del tema desde las preferencias almacenadas.
     */
    init {
        viewModelScope.launch {
            _isDarkTheme.value = themePreferences.isDarkTheme.first()
        }
    }

    /**
     * ### Función que alterna entre el tema claro y oscuro.
     * Actualiza el estado interno y guarda la preferencia en almacenamiento persistente.
     */
    fun toggleTheme() {
        viewModelScope.launch {
            // Alterna el estado del tema oscuro
            val newTheme = !_isDarkTheme.value

            // Actualiza el estado del tema oscuro
            _isDarkTheme.value = newTheme

            // Guarda la nueva preferencia de tema en almacenamiento persistente
            themePreferences.saveTheme(newTheme)
        }
    }
}