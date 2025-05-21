package com.jluqgon214.hogarmate.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extensión para crear una instancia de DataStore asociada al contexto.
private val Context.dataStore by preferencesDataStore(name = "theme_preferences")

/**
 * # Clase que gestiona las preferencias relacionadas con el tema de la aplicación.
 *
 * Utiliza DataStore para almacenar y recuperar la preferencia del tema (claro u oscuro).
 *
 * @param context Contexto de la aplicación necesario para acceder a DataStore.
 */
class ThemePreferences(private val context: Context) {
    // Clave para almacenar la preferencia del tema en DataStore.
    private val THEME_KEY = booleanPreferencesKey("dark_theme")

    /**
     * ### Flujo que emite el estado actual del tema (oscuro o claro).
     *
     * @return `Flow<Boolean>` que indica si el tema oscuro está activado.
     *         Devuelve `false` por defecto (tema claro).
     */
    val isDarkTheme: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[THEME_KEY] == true // Tema claro por defecto
        }

    /**
     * ### Guarda la preferencia del tema en DataStore.
     *
     * @param isDark `true` para activar el tema oscuro, `false` para el tema claro.
     */
    suspend fun saveTheme(isDark: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDark
        }
    }
}