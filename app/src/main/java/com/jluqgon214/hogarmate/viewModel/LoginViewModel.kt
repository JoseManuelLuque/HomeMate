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

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _loginResponse = MutableStateFlow<LoginResponse?>(null)
    val loginResponse: StateFlow<LoginResponse?> = _loginResponse

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _JWT = MutableStateFlow<String?>(null)
    val JWT: StateFlow<String?> = _JWT

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _loginCorrecto = MutableStateFlow<Boolean>(false)
    val loginCorrecto: StateFlow<Boolean> = _loginCorrecto

    fun setLoginCorrecto(value: Boolean) {
        _loginCorrecto.value = value
    }

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun setErrorMessage(message: String?) {
        _errorMessage.value = message
    }

    fun setJWT(jwt: String?) {
        _JWT.value = jwt
    }


    fun login() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response: Response<LoginResponse> =
                    repository.login(_email.value, _password.value)
                if (response.isSuccessful) {
                    _loginResponse.value = response.body()
                    setLoginCorrecto(true)
                } else {
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
                        _errorMessage.value = "Inicio de sesión fallido"
                    }
                }
            } catch (e: Exception) {
                _errorMessage.value = "Request failed: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}