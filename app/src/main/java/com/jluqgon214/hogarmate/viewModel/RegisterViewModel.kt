package com.jluqgon214.hogarmate.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jluqgon214.hogarmate.model.RegisterResponse
import com.jluqgon214.hogarmate.repository.RegisterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.net.SocketTimeoutException

class RegisterViewModel : ViewModel() {
    private val repository = RegisterRepository()

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    fun setUsername(username: String) {
        _username.value = username
    }


    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    fun setEmail(email: String) {
        _email.value = email
    }


    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    fun setPassword(password: String) {
        _password.value = password
    }

    private val _passwordRepeat = MutableStateFlow("")
    val passwordRepeat: StateFlow<String> = _passwordRepeat

    fun setRepeatPassword(password: String) {
        _passwordRepeat.value = password
    }

    /*private val _hogarId = MutableStateFlow(null)
    val hogar: StateFlow<> = _hogarId*/


    private val _registerResponse = MutableStateFlow<RegisterResponse?>(null)
    val registerResponse: StateFlow<RegisterResponse?> = _registerResponse

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun register() {
        viewModelScope.launch {
            try {
                val response: Response<RegisterResponse> = repository.register(
                    _username.value,
                    _email.value,
                    _password.value,
                    _passwordRepeat.value
                )
                if (response.isSuccessful) {
                    _registerResponse.value = response.body()
                } else {
                    // Obtener el string del error y parsearlo
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
                        _errorMessage.value = "Error desconocido"
                    }
                }
            } catch (e: SocketTimeoutException) {
                _errorMessage.value = "Comprueba tu conexión a Internet"
            } catch (e: Exception) {
                _errorMessage.value = "Request failed: ${e.message}"
            }
        }
    }
}