package com.jluqgon214.hogarmate.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jluqgon214.hogarmate.screens.LoginScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold {
        NavHost(navController, startDestination = "loginScreen") {
            composable("loginScreen") {
                LoginScreen()
            }
        }
    }
}