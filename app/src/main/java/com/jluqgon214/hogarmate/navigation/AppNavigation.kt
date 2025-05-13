package com.jluqgon214.hogarmate.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jluqgon214.hogarmate.components.AdminAnimatedBottomBar
import com.jluqgon214.hogarmate.components.AnimatedBottomBar
import com.jluqgon214.hogarmate.components.FAB
import com.jluqgon214.hogarmate.components.TopTittle
import com.jluqgon214.hogarmate.screens.AdminScreen
import com.jluqgon214.hogarmate.screens.HomeScreen
import com.jluqgon214.hogarmate.screens.LoginScreen
import com.jluqgon214.hogarmate.screens.ProfileScreen
import com.jluqgon214.hogarmate.screens.RegisterScreen
import com.jluqgon214.hogarmate.screens.SettingsScreen
import com.jluqgon214.hogarmate.screens.TasksScreen
import com.jluqgon214.hogarmate.test.TestScreen
import com.jluqgon214.hogarmate.viewModel.AdminViewModel
import com.jluqgon214.hogarmate.viewModel.AppViewModel
import com.jluqgon214.hogarmate.viewModel.LoginViewModel
import com.jluqgon214.hogarmate.viewModel.NavigationViewModel
import com.jluqgon214.hogarmate.viewModel.ProfileViewModel
import com.jluqgon214.hogarmate.viewModel.RegisterViewModel
import com.jluqgon214.hogarmate.viewModel.TasksViewModel
import com.jluqgon214.hogarmate.viewModel.ThemeViewModel

@Composable
fun AppNavigation(
    themeViewModel: ThemeViewModel,
) {
    // Navigation Controller
    val navController = rememberNavController()

    // ViewModels
    val loginViewModel = LoginViewModel()
    val registerViewModel = RegisterViewModel()
    val tasksViewModel = TasksViewModel()
    val profileViewModel = ProfileViewModel()
    val navigaionViewModel = NavigationViewModel()
    val adminViewModel = AdminViewModel()
    val appViewModel = AppViewModel()

    // Variables de estado
    val textoTop by appViewModel.textoTop.collectAsState()
    val showFAB by appViewModel.showFAB.collectAsState()
    val showBottomBar by appViewModel.showBottomBar.collectAsState()
    val selectedBottomBarIndex by appViewModel.selectedBottomBarIndex.collectAsState()

    Scaffold(
        topBar = { TopTittle(texto = textoTop) },
        floatingActionButton = {
            FAB(onFabClick = {
                tasksViewModel.setShowDialog(true)
            }, show = showFAB)
        },
        bottomBar = {
            if (navigaionViewModel.isAdmin.collectAsState().value) {
                AdminAnimatedBottomBar(
                    show = showBottomBar,
                    selectedIndex = selectedBottomBarIndex,
                    onItemSelected = { index -> appViewModel.setSelectedBottomBarIndex(index) },
                    onNavigate = { index -> navigateToIndex(navController, index) }
                )
            } else {
                AnimatedBottomBar(
                    show = showBottomBar,
                    selectedIndex = selectedBottomBarIndex,
                    onItemSelected = { index -> appViewModel.setSelectedBottomBarIndex(index) },
                    onNavigate = { index -> navigateToIndex(navController, index) }
                )
            }
        },
    ) { contentPadding ->
        NavHost(navController, startDestination = "loginScreen") {
            composable("loginScreen") {
                // Cambiar el título de la barra superior
                appViewModel.setTextoTop("Iniciar sesión")

                // Ocultar el FAB y la barra inferior
                appViewModel.setShowFAB(false)
                appViewModel.setShowBottomBar(false)
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
                HomeScreen()

                LaunchedEffect(Unit) {
                    // Comprobar si el usuario es administrador
                    navigaionViewModel.comprobarAdmin()
                }
            }

            composable("tasksScreen") {
                appViewModel.setTextoTop("Tareas")
                appViewModel.setShowFAB(true)
                appViewModel.setShowBottomBar(true)
                TasksScreen(tasksViewModel = tasksViewModel, paddingValues = contentPadding)
            }

            composable("profileScreen") {
                appViewModel.setTextoTop("Perfil")
                appViewModel.setShowFAB(false)
                appViewModel.setShowBottomBar(true)
                ProfileScreen(
                    navController = navController,
                    loginViewModel = loginViewModel,
                    AppViewModel = appViewModel,
                    profileViewModel = profileViewModel
                )
            }

            composable("settingsScreen") {
                appViewModel.setTextoTop("Configuración")
                appViewModel.setShowFAB(false)
                appViewModel.setShowBottomBar(true)
                SettingsScreen(themeViewModel = themeViewModel, paddingValues = contentPadding, navController = navController)
            }

            composable("adminScreen") {
                appViewModel.setTextoTop("Administración")
                appViewModel.setShowFAB(false)
                appViewModel.setShowBottomBar(true)
                // Aquí puedes agregar la pantalla de administración
                AdminScreen(
                    adminViewModel = adminViewModel,
                    paddingValues = contentPadding,
                    tasksViewModel = tasksViewModel
                )
            }

            composable("testScreen") {
                TestScreen(tasksViewModel = tasksViewModel, paddingValues = contentPadding)
            }
        }
    }
}

private fun navigateToIndex(navController: NavController, index: Int) {
    when (index) {
        0 -> navController.navigate("homeScreen") { launchSingleTop = true }
        1 -> navController.navigate("tasksScreen") { launchSingleTop = true }
        2 -> navController.navigate("profileScreen") { launchSingleTop = true }
        3 -> navController.navigate("settingsScreen") { launchSingleTop = true }
        4 -> navController.navigate("adminScreen") { launchSingleTop = true }
    }
}