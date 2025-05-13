package com.jluqgon214.hogarmate.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jluqgon214.hogarmate.client.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class NavigationViewModel : ViewModel() {
    private val apiService = RetrofitClient.instance

    private val _isAdmin = MutableStateFlow(false)
    val isAdmin: StateFlow<Boolean> = _isAdmin

    fun comprobarAdmin() {
        viewModelScope.launch {
            try {
                val response = apiService.comprobarAdmin().awaitResponse()
                if (response.isSuccessful) {
                    _isAdmin.value = response.body() == true
                } else {
                    _isAdmin.value = false
                }
            } catch (e: Exception) {
                _isAdmin.value = false
            }
        }
    }
}

