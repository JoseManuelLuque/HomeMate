package com.jluqgon214.hogarmate

import androidx.compose.material3.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jluqgon214.hogarmate.screens.LoginScreen
import com.jluqgon214.hogarmate.viewModel.LoginViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    // Test 1: Comprobar que el campo de correo electrónico es visible
    @Test
    fun testEmailFieldIsVisible() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val loginViewModel = LoginViewModel()

            LoginScreen(
                loginViewModel = loginViewModel,
                navController = navController
            )
        }


        composeTestRule.onNodeWithText("Correo Electrónico").assertIsDisplayed()
    }

    // Test 2: Comprobar que el campo de contraseña es visible
    @Test
    fun testPasswordFieldIsVisible() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val loginViewModel = LoginViewModel()

            LoginScreen(
                loginViewModel = loginViewModel,
                navController = navController
            )
        }


        composeTestRule.onNodeWithText("Contraseña").assertIsDisplayed()
    }

    // Test 3: Comprobar que el botón de inicio de sesión es visible
    @Test
    fun testLoginButtonIsVisible() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val loginViewModel = LoginViewModel()

            LoginScreen(
                loginViewModel = loginViewModel,
                navController = navController
            )
        }

        composeTestRule.onNodeWithText("Iniciar sesión").assertIsDisplayed()
    }

    // Test 4: Comprobar que el campo de correo electrónico acepta la entrada
    @Test
    fun testEmailFieldAcceptsInput() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val loginViewModel = LoginViewModel()

            LoginScreen(
                loginViewModel = loginViewModel,
                navController = navController
            )
        }

        val emailInput = "test@gmail.com"
        composeTestRule.onNodeWithText("Correo Electrónico").performTextInput(emailInput)
        composeTestRule.onNodeWithText(emailInput).assertIsDisplayed()
    }

    // Test 5: Comprobar que el botón de inicio de sesión responde al clic
    @Test
    fun testLoginButtonRespondsToClick() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val loginViewModel = LoginViewModel()

            LoginScreen(
                loginViewModel = loginViewModel,
                navController = navController
            )
        }

        composeTestRule.onNodeWithText("Iniciar sesión").performClick()
    }

    // Test 6: Comprobar que el mensaje de error se muestra correctamente (Forzando que el error sea el que pongo)
    @Test
    fun testErrorMessageIsDisplayed() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val loginViewModel = LoginViewModel().apply {
                setErrorMessage("Error al iniciar sesión")
            }

            LoginScreen(
                loginViewModel = loginViewModel,
                navController = navController
            )
        }

        composeTestRule.onNodeWithText("Error al iniciar sesión").assertIsDisplayed()
    }

    // Test 7: Compobar el mensaje de error con email erroneo
    @Test
    fun testErrorMessageWithInvalidEmail() {
        lateinit var loginViewModel: LoginViewModel
        composeTestRule.setContent {
            val navController = rememberNavController()
            loginViewModel = LoginViewModel()

            LoginScreen(
                loginViewModel = loginViewModel,
                navController = navController
            )
        }

        val invalidEmail = "email@gmail.com"
        val invalidPassword = "123456"
        composeTestRule.onNodeWithText("Correo Electrónico").performTextInput(invalidEmail)
        composeTestRule.onNodeWithText("Contraseña").performTextInput(invalidPassword)
        composeTestRule.onNodeWithText("Iniciar sesión").performClick()

        composeTestRule.waitUntil(10000) {
            loginViewModel.errorMessage.value != null
        }
    }

    // Test 8: Comprobar que el botón de "Registrarse" navega a la pantalla de registro
    @Test
    fun testNavigateToRegisterScreen() {
        lateinit var navController: androidx.navigation.NavController

        composeTestRule.setContent {
            navController = rememberNavController()
            val loginViewModel = LoginViewModel()

            // Configura un gráfico de navegación de prueba
            androidx.navigation.compose.NavHost(
                navController = navController,
                startDestination = "loginScreen"
            ) {
                composable("loginScreen") {
                    LoginScreen(
                        loginViewModel = loginViewModel,
                        navController = navController
                    )
                }
                composable("registerScreen") {
                    // Pantalla de registro simulada
                    Text("Pantalla de Registro")
                }
            }
        }

        // Simula el clic en el botón "Registrarse"
        composeTestRule.onNodeWithText("Registrarse").performClick()
        assert(navController.currentDestination?.route == "registerScreen")
    }

    // Test 9: Comprobar que en un login correcto se navega a la pantalla de inicio
    @Test
    fun testLogin() {
        lateinit var navController: androidx.navigation.NavController

        composeTestRule.setContent {
            navController = rememberNavController()
            val loginViewModel = LoginViewModel()

            // Configura un gráfico de navegación de prueba
            androidx.navigation.compose.NavHost(
                navController = navController,
                startDestination = "loginScreen"
            ) {
                composable("loginScreen") {
                    LoginScreen(
                        loginViewModel = loginViewModel,
                        navController = navController
                    )
                }
                composable("homeScreen") {
                    // Pantalla de inicio simulada
                    Text("Pantalla de Inicio")
                }
            }
        }

        // Simula la entrada de datos en los campos de correo y contraseña
        composeTestRule.onNodeWithText("Correo Electrónico").performTextInput("josema@gmail.com")
        composeTestRule.onNodeWithText("Contraseña").performTextInput("123456")

        // Simula el clic en el botón "Iniciar sesión"
        composeTestRule.onNodeWithText("Iniciar sesión").performClick()

        // Verifica que la navegación ocurrió hacia la pantalla "homeScreen"
        composeTestRule.waitUntil(10000) {
            navController.currentDestination?.route == "homeScreen"
        }
        assert(navController.currentDestination?.route == "homeScreen")
    }

    // Test 10: Comprobar que el indicador de carga se muestra durante el inicio de sesión
    @Test
    fun testLoadingIndicatorIsDisplayedWhileLoggingIn() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val loginViewModel = LoginViewModel()

            LoginScreen(
                loginViewModel = loginViewModel,
                navController = navController
            )
        }

        // Verifica que el indicador de carga está visible
        composeTestRule.onNodeWithText("Iniciar sesión").performClick()
        composeTestRule.onNodeWithTag("CircularProgressIndicator").assertIsDisplayed()
    }
}