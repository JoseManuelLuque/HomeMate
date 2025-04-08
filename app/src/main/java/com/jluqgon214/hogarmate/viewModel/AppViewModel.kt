package com.jluqgon214.hogarmate.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AppViewModel : ViewModel() {
    private val _textoTop = MutableStateFlow("")
    val textoTop: StateFlow<String> = _textoTop

    private val _showFAB = MutableStateFlow(false)
    val showFAB: StateFlow<Boolean> = _showFAB

    fun setTextoTop(texto: String) {
        _textoTop.value = texto
    }

    fun setShowFAB(show: Boolean) {
        _showFAB.value = show
    }
}