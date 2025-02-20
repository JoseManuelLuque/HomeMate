package com.jluqgon214.hogarmate.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jluqgon214.hogarmate.screens.LoginScreen
import com.jluqgon214.hogarmate.screens.RegisterScreen
import com.jluqgon214.hogarmate.viewModel.LoginViewModel
import com.jluqgon214.hogarmate.viewModel.RegisterViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = LoginViewModel()
    val registerViewModel: RegisterViewModel = RegisterViewModel()

    Scaffold {
        NavHost(navController, startDestination = "loginScreen") {
            composable("loginScreen") {
                LoginScreen(viewModel = loginViewModel, navController = navController)
            }
            composable("registerScreen") {
                RegisterScreen(viewModel = registerViewModel, navController = navController)
            }
        }
    }
}