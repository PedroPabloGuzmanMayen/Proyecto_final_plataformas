package com.example.proyectofinal.Interface.ListView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectofinal.Interface.TaskScreen.color
import com.example.proyectofinal.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddListScreen(navController: NavController, sharedViewModel: ListViewModel, username: String?){
    Scaffold(
        topBar = {
            TopAppBar(title = {
                androidx.compose.material.Text(
                    text = stringResource(R.string.CreateList),
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                )
            },


                navigationIcon = {
                    androidx.compose.material.IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                })


        }
    ){ innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding), contentAlignment = Alignment.Center, ){
            var name by remember { mutableStateOf("") }

            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth() ){

                TextField(
                    value = name,
                    onValueChange = {name = it },
                    modifier = Modifier,
                    label = { Text(stringResource(id = R.string.Listname)) },
                )
                Button(
                    modifier = Modifier.padding(top = 16.dp),
                    onClick = {
                        sharedViewModel.addList(username.orEmpty(), name)
                        navController.popBackStack()

                    },
                    colors = ButtonDefaults.buttonColors("#fcb603".color)
                ){
                    Text(text = stringResource(id = R.string.CreateList))
                }

    }

        }

    }

    }
