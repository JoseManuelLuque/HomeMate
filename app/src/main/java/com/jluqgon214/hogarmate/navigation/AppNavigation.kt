package com.jluqgon214.hogarmate.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jluqgon214.hogarmate.components.FAB
import com.jluqgon214.hogarmate.components.TopTittle
import com.jluqgon214.hogarmate.screens.HomeScreen
import com.jluqgon214.hogarmate.screens.LoginScreen
import com.jluqgon214.hogarmate.screens.RegisterScreen
import com.jluqgon214.hogarmate.viewModel.AppViewModel
import com.jluqgon214.hogarmate.viewModel.HomeViewModel
import com.jluqgon214.hogarmate.viewModel.LoginViewModel
import com.jluqgon214.hogarmate.viewModel.RegisterViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val loginViewModel = LoginViewModel()
    val registerViewModel = RegisterViewModel()
    val homeViewModel = HomeViewModel()
    val appViewModel: AppViewModel = viewModel()

    val textoTop by appViewModel.textoTop.collectAsState()
    val showFAB by appViewModel.showFAB.collectAsState()

    Scaffold(
        topBar = { TopTittle(texto = textoTop) },
        floatingActionButton = { FAB(onFabClick = { TODO() }, show = showFAB) }
    ) { contentPadding ->
        NavHost(navController, startDestination = "loginScreen") {
            composable("loginScreen") {
                appViewModel.setTextoTop("Iniciar sesión")
                appViewModel.setShowFAB(false)
                LoginScreen(loginViewModel = loginViewModel, navController = navController)
            }
            composable("registerScreen") {
                appViewModel.setTextoTop("Registrarse")
                appViewModel.setShowFAB(false)
                RegisterScreen(registerViewModel = registerViewModel, navController = navController)
            }
            composable("homeScreen") {
                appViewModel.setTextoTop("Pantalla Principal")
                appViewModel.setShowFAB(true)
                HomeScreen(homeViewModel = homeViewModel, paddingValues = contentPadding)
            }
        }
    }
}