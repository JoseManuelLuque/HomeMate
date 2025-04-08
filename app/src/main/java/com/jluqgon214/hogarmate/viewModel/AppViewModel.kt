package com.jluqgon214.hogarmate.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AppViewModel : ViewModel() {
    private val _textoTop = MutableStateFlow("")
    val textoTop: StateFlow<String> = _textoTop

    fun setTextoTop(texto: String) {
        _textoTop.value = texto
    }

    private val _showFAB = MutableStateFlow(false)
    val showFAB: StateFlow<Boolean> = _showFAB

    fun setShowFAB(show: Boolean) {
        _showFAB.value = show
    }

    private val _showBottomBar = MutableStateFlow(false)
    val showBottomBar: StateFlow<Boolean> = _showBottomBar

    fun setShowBottomBar(show: Boolean) {
        _showBottomBar.value = show
    }

    private val _selectedBottomBarIndex = MutableStateFlow(0)
    val selectedBottomBarIndex: StateFlow<Int> = _selectedBottomBarIndex

    fun setSelectedBottomBarIndex(index: Int) {
        _selectedBottomBarIndex.value = index
    }
}