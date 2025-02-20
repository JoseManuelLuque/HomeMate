package com.jluqgon214.hogarmate.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jluqgon214.hogarmate.model.LoginResponse
import com.jluqgon214.hogarmate.repository.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val repository = LoginRepository()

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _loginResponse = MutableStateFlow<LoginResponse?>(null)
    val loginResponse: StateFlow<LoginResponse?> = _loginResponse

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun setUsername(username: String) {
        _username.value = username
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun login() {
        viewModelScope.launch {
            try {
                val response: Response<LoginResponse> = repository.login(_username.value, _password.value)
                if (response.isSuccessful) {
                    _loginResponse.value = response.body()
                } else {
                    // Obtener el contenido del error y parsearlo
                    val errorJson = response.errorBody()?.string()
                    errorJson?.let {
                        try {
                            val jsonObject = JSONObject(it)
                            val errorMessage = jsonObject.getString("message")
                            _errorMessage.value = errorMessage
                        } catch (e: JSONException) {
                            _errorMessage.value = it
                        }
                    } ?: run {
                        _errorMessage.value = "Login failed"
                    }
                }
            } catch (e: Exception) {
                _errorMessage.value = "Request failed: ${e.message}"
            }
        }
    }
}