package com.example.proyectofinal.Navigation

sealed class AppScreens(val route: String) {
    object Login : AppScreens("Login")
    object Register : AppScreens("Register")
    object createList: AppScreens("createList")
    object Home : AppScreens("Home")
    object TaskDetails: AppScreens("TaskDetails")

    object ActivityList: AppScreens("ActivityList")

    object deleteScreen: AppScreens("deleteScreen")

    object firstScreen: AppScreens("firstScreen")
}
