package com.example.proyectofinal.Navigation

sealed class AppScreens(val route: String) {
    object Login : AppScreens("Login")
    object Register : AppScreens("Register")
    object Home : AppScreens("Home")
    object TaskDetails: AppScreens("TaskDetails")
}
