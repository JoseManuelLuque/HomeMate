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
import com.jluqgon214.hogarmate.components.AnimatedBottomBar
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
    val showBottomBar by appViewModel.showBottomBar.collectAsState()
    val selectedBottomBarIndex by appViewModel.selectedBottomBarIndex.collectAsState()

    Scaffold(
        topBar = { TopTittle(texto = textoTop) },
        floatingActionButton = { FAB(onFabClick = { TODO() }, show = showFAB) },
        bottomBar = {  AnimatedBottomBar(
            show = showBottomBar,
            selectedIndex = selectedBottomBarIndex,
            onItemSelected = { index -> appViewModel.setSelectedBottomBarIndex(index) },
            onNavigate = { index ->
                when (index) {
                    0 -> navController.navigate("homeScreen") { launchSingleTop = true }
                    1 -> navController.navigate("tasksScreen") { launchSingleTop = true }
                    2 -> navController.navigate("profileScreen") { launchSingleTop = true }
                    3 -> navController.navigate("settingsScreen") { launchSingleTop = true }
                }
            }
        ) },
    ) { contentPadding ->
        NavHost(navController, startDestination = "loginScreen") {
            composable("loginScreen") {
                appViewModel.setTextoTop("Iniciar sesión")
                appViewModel.setShowFAB(false)
                appViewModel.setShowBottomBar(true)
                LoginScreen(loginViewModel = loginViewModel, navController = navController)
            }

            composable("registerScreen") {
                appViewModel.setTextoTop("Registrarse")
                appViewModel.setShowFAB(false)
                appViewModel.setShowBottomBar(false)
                RegisterScreen(registerViewModel = registerViewModel, navController = navController)
            }

            composable("homeScreen") {
                appViewModel.setTextoTop("Pantalla Principal")
                appViewModel.setShowFAB(true)
                appViewModel.setShowBottomBar(true)
                HomeScreen(homeViewModel = homeViewModel, paddingValues = contentPadding)
            }

            composable("tasksScreen") {
                appViewModel.setTextoTop("Tareas")
                appViewModel.setShowFAB(false)
                appViewModel.setShowBottomBar(true)
                // TODO("Implementar pantalla de tareas")
            }

            composable("profileScreen") {
                appViewModel.setTextoTop("Perfil")
                appViewModel.setShowFAB(false)
                appViewModel.setShowBottomBar(true)
                // TODO("Implementar pantalla de perfil")
            }

            composable("settingsScreen") {
                appViewModel.setTextoTop("Configuración")
                appViewModel.setShowFAB(false)
                appViewModel.setShowBottomBar(true)
                // TODO("Implementar pantalla de configuración")
            }
        }
    }
}