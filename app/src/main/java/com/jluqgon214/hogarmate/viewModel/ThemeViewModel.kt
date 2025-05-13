package com.jluqgon214.hogarmate.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jluqgon214.hogarmate.preferences.ThemePreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ThemeViewModel(context: Context) : ViewModel() {
    private val themePreferences = ThemePreferences(context)
    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme

    // Inicializa el estado del tema desde las preferencias
    init {
        viewModelScope.launch {
            _isDarkTheme.value = themePreferences.isDarkTheme.first()
        }
    }

    fun toggleTheme() {
        viewModelScope.launch {
            val newTheme = !_isDarkTheme.value
            _isDarkTheme.value = newTheme
            themePreferences.saveTheme(newTheme)
        }
    }
}