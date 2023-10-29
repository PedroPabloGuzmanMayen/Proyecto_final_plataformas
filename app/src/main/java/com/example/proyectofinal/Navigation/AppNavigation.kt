package com.example.proyectofinal.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinal.Interface.TaskScreen


@Composable

fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.TaskDetails.route){
        composable(AppScreens.Login.route){

        }
        composable(AppScreens.Register.route){

        }
        composable(AppScreens.Home.route){

        }
        composable(AppScreens.TaskDetails.route){
            TaskScreen(navController = navController)
        }
    }

}
