package com.example.proyectofinal.Interface.TaskScreen.View

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectofinal.Interface.TaskScreen.ViewModel.ActivityViewModel
import com.example.proyectofinal.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun deleteActivity(navController: NavController, sharedViewModel: ActivityViewModel, username: String?, listName:String?, activity:String?){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.DeleteTask), fontSize = 30.sp, textAlign = TextAlign.Center)
                },

                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )


        }
// Aquí modifique
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).fillMaxSize()){
// Aquí termina , horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
            Button(

                onClick = {
                    sharedViewModel.deleteActivity(username.orEmpty(), listName.orEmpty(), activity.orEmpty())
                    navController.popBackStack()


                },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(Color.Red)
            ) {
                androidx.compose.material3.Text(stringResource(id = R.string.DeleteTask), color = Color.Black)
            }
        }
    }
}