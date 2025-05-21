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
import com.jluqgon214.hogarmate.components.CustomBottomBar
import com.jluqgon214.hogarmate.components.FAB
import com.jluqgon214.hogarmate.components.TopTittle
import com.jluqgon214.hogarmate.screens.AdminScreen
import com.jluqgon214.hogarmate.screens.HomeScreen
import com.jluqgon214.hogarmate.screens.LoginScreen
import com.jluqgon214.hogarmate.screens.ProfileScreen
import com.jluqgon214.hogarmate.screens.RegisterScreen
import com.jluqgon214.hogarmate.screens.SettingsScreen
import com.jluqgon214.hogarmate.screens.TasksScreen
import com.jluqgon214.hogarmate.viewModel.AdminViewModel
import com.jluqgon214.hogarmate.viewModel.AppViewModel
import com.jluqgon214.hogarmate.viewModel.LoginViewModel
import com.jluqgon214.hogarmate.viewModel.ProfileViewModel
import com.jluqgon214.hogarmate.viewModel.RegisterViewModel
import com.jluqgon214.hogarmate.viewModel.TasksViewModel
import com.jluqgon214.hogarmate.viewModel.ThemeViewModel

/**
 * # Pantalla composable que define la navegación principal de la aplicación.
 *
 * Este componente utiliza `NavHost` para gestionar las diferentes pantallas de la aplicación,
 * incluyendo la lógica de navegación y la configuración de los elementos de la interfaz de usuario
 * como la barra superior, el FAB y la barra inferior.
 *
 * @param themeViewModel ViewModel que gestiona el tema de la aplicación.
 */
@Composable
fun AppNavigation(
    themeViewModel: ThemeViewModel,
) {
    // Controlador de navegación
    val navController = rememberNavController()

    // ViewModels
    val loginViewModel = LoginViewModel()
    val registerViewModel = RegisterViewModel()
    val tasksViewModel = TasksViewModel()
    val profileViewModel = ProfileViewModel()
    val adminViewModel = AdminViewModel()
    val appViewModel = AppViewModel()

    // Variables de estado
    val textoTop by appViewModel.textoTop.collectAsState()
    val showFAB by appViewModel.showFAB.collectAsState()
    val showBottomBar by appViewModel.showBottomBar.collectAsState()
    val selectedBottomBarIndex by appViewModel.selectedBottomBarIndex.collectAsState()
    val isAdmin by adminViewModel.isAdmin.collectAsState()

    Scaffold(
        topBar = { TopTittle(texto = textoTop) }, // Barra superior con el título dinámico.
        floatingActionButton = {
            FAB(onFabClick = {
                tasksViewModel.setShowDialog(true)
            }, show = showFAB) // Botón de acción flotante.
        },
        bottomBar = {
            LaunchedEffect(Unit) {
                // Verifica si el usuario es administrador.
                adminViewModel.comprobarAdmin()
            }

            CustomBottomBar(
                isAdmin = isAdmin,
                showBottomBar = showBottomBar,
                selectedBottomBarIndex = selectedBottomBarIndex,
                onItemSelected = { index -> appViewModel.setSelectedBottomBarIndex(index) },
                onNavigate = { index -> navigateToIndex(navController, index) },
            ) // Barra inferior personalizada.
        },
    ) { contentPadding ->
        // Configuración de las rutas de navegación.
        NavHost(navController, startDestination = "loginScreen") {
            composable("loginScreen") {
                appViewModel.setTextoTop("Iniciar sesión")
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
                appViewModel.setSelectedBottomBarIndex(0)
                HomeScreen()

                LaunchedEffect(Unit) {
                    adminViewModel.comprobarAdmin()
                }
            }

            composable("tasksScreen") {
                appViewModel.setTextoTop("Tareas")
                appViewModel.setShowFAB(true)
                appViewModel.setShowBottomBar(true)
                appViewModel.setSelectedBottomBarIndex(1)
                TasksScreen(
                    tasksViewModel = tasksViewModel,
                    paddingValues = contentPadding,
                    navController = navController
                )
            }

            composable("profileScreen") {
                appViewModel.setTextoTop("Perfil")
                appViewModel.setShowFAB(false)
                appViewModel.setShowBottomBar(true)
                appViewModel.setSelectedBottomBarIndex(2)

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

                adminViewModel.comprobarAdmin()
                appViewModel.setSelectedBottomBarIndex(3)

                SettingsScreen(
                    themeViewModel = themeViewModel,
                    paddingValues = contentPadding,
                    navController = navController,
                    appViewModel = appViewModel,
                    adminViewModel = adminViewModel
                )
            }

            composable("adminScreen") {
                appViewModel.setTextoTop("Administración")
                appViewModel.setShowFAB(false)
                appViewModel.setShowBottomBar(true)
                appViewModel.setSelectedBottomBarIndex(4)
                AdminScreen(
                    adminViewModel = adminViewModel,
                    paddingValues = contentPadding,
                    tasksViewModel = tasksViewModel,
                    profileViewModel = profileViewModel
                )
            }
        }
    }
}

/**
 * ### Función auxiliar para navegar a una pantalla específica según el índice seleccionado.
 *
 * @param navController Controlador de navegación.
 * @param index Índice de la pantalla a la que se desea navegar.
 */
private fun navigateToIndex(navController: NavController, index: Int) {
    when (index) {
        0 -> navController.navigate("homeScreen") { launchSingleTop = true }
        1 -> navController.navigate("tasksScreen") { launchSingleTop = true }
        2 -> navController.navigate("profileScreen") { launchSingleTop = true }
        3 -> navController.navigate("settingsScreen") { launchSingleTop = true }
        4 -> navController.navigate("adminScreen") { launchSingleTop = true }
    }
}