package com.example.proyectofinal.Navigation

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinal.Interface.ListScreen
import com.example.proyectofinal.Interface.SharedViewModel
import com.example.proyectofinal.Interface.TaskScreen


@Composable

fun Navigation(){
    val navController = rememberNavController()
    val sharedViewModel: SharedViewModel = viewModel()
    NavHost(navController = navController, startDestination = AppScreens.Login.route){
        composable(AppScreens.Login.route){
            LoginScreen(navController)
        }
        composable(AppScreens.Register.route){

        }
        composable(AppScreens.Home.route){
            ListScreen(navController, sharedViewModel = sharedViewModel)
        }
        composable(AppScreens.TaskDetails.route){
            TaskScreen(navController = navController, sharedViewModel = sharedViewModel)
        }
    }

}
