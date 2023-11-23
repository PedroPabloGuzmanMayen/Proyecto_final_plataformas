package com.example.proyectofinal.Navigation

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyectofinal.Interface.LOGIN.Register
import com.example.proyectofinal.Interface.TaskScreen.ActivityScreen
import com.example.proyectofinal.Interface.ListView.AddListScreen
import com.example.proyectofinal.Interface.ListView.ListScreen
import com.example.proyectofinal.Interface.ListView.ListViewModel
import com.example.proyectofinal.Interface.TaskScreen.ActivityViewModel
import com.example.proyectofinal.Interface.TaskScreen.TaskScreen
import com.example.proyectofinal.Interface.TaskScreen.deleteActivity
import com.example.proyectofinal.Interface.firstScreen


@Composable

fun Navigation(){
    val navController = rememberNavController()

    val listviewModel: ListViewModel = viewModel()
    val activityViewModel: ActivityViewModel = viewModel()
    NavHost(navController = navController, startDestination = AppScreens.firstScreen.route){
        composable(AppScreens.firstScreen.route){
            firstScreen(navController = navController)
        }
        composable(AppScreens.Login.route){
            LoginScreen(navController)
        }
        composable(AppScreens.Register.route){
            Register(navController = navController)
        }

        composable(AppScreens.createList.route + "/{username}",
        arguments = listOf(navArgument(name="username"){
            type = NavType.StringType
        })){ backStackEntry ->
            AddListScreen(navController = navController, sharedViewModel = listviewModel, username = backStackEntry.arguments?.getString("username"))
        }
        composable(AppScreens.Home.route + "/{username}",
            arguments = listOf(navArgument(name="username"){
                type = NavType.StringType
        })) { backStackEntry ->
            ListScreen(navController, sharedViewModel = listviewModel, username = backStackEntry.arguments?.getString("username"))
        }
        composable(AppScreens.ActivityList.route + "/{username}/{listName}",
            arguments = listOf(navArgument(name="username"){
                type = NavType.StringType
            }, navArgument(name="listName"){
                type = NavType.StringType
            })) { backStackEntry ->
            ActivityScreen(navController, sharedViewModel = activityViewModel, username = backStackEntry.arguments?.getString("username"), listName = backStackEntry.arguments?.getString("listName"))
        }
        composable(AppScreens.TaskDetails.route + "/{username}/{listName}",
            arguments = listOf(navArgument(name="username"){
                type = NavType.StringType
            }, navArgument(name="listName"){
                type = NavType.StringType
            })) { backStackEntry ->
            TaskScreen(navController = navController, sharedViewModel = activityViewModel, username = backStackEntry.arguments?.getString("username"), listName = backStackEntry.arguments?.getString("listName"))
        }
        composable(AppScreens.deleteScreen.route + "/{username}/{listName}/{activityName}",
            arguments = listOf(navArgument(name="username"){
                type = NavType.StringType
            }, navArgument(name="listName"){
                type = NavType.StringType
            }, navArgument(name="activityName"){
                type = NavType.StringType
            })) { backStackEntry ->
            deleteActivity(navController = navController, sharedViewModel = activityViewModel, username = backStackEntry.arguments?.getString("username"), listName = backStackEntry.arguments?.getString("listName"), activity = backStackEntry.arguments?.getString("activityName"))
        }
    }

}
