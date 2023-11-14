package com.example.proyectofinal.Interface

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddListScreen(navController: NavController, sharedViewModel: SharedViewModel, username: String?){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center, ){
        var name by remember { mutableStateOf("Nombre lista") }

        Column(){

            TextField(
                value = name,
                onValueChange = {name = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Nombre de la lista") },
            )
            Button(
                modifier = Modifier.padding(top = 16.dp),
                onClick = {
                    sharedViewModel.addList(username.orEmpty(), name)
                    navController.popBackStack()



                     }
            ){
                Text(text = "Crear lista")
            }



            /*
            TextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(id = R.string.User)) },
        )
             */

        }

    }

    }
