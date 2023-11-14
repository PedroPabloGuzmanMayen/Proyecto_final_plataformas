package com.example.proyectofinal.Navigation

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyectofinal.Interface.AddListScreen
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

        composable(AppScreens.createList.route + "/{username}",
        arguments = listOf(navArgument(name="username"){
            type = NavType.StringType
        })){ backStackEntry ->
            AddListScreen(navController = navController, sharedViewModel = sharedViewModel, username = backStackEntry.arguments?.getString("username"))
        }
        composable(AppScreens.Home.route + "/{username}",
            arguments = listOf(navArgument(name="username"){
                type = NavType.StringType
        })) { backStackEntry ->
            ListScreen(navController, sharedViewModel = sharedViewModel, username = backStackEntry.arguments?.getString("username"))
        }
        composable(AppScreens.TaskDetails.route){
            TaskScreen(navController = navController, sharedViewModel = sharedViewModel)
        }
    }

}
