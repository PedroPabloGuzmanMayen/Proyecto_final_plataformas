package com.example.proyectofinal.Interface

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectofinal.Task.Task
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectofinal.Navigation.AppScreens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    val contentList = sharedViewModel.contentList
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Tus tareas", fontSize = 20.sp, textAlign = TextAlign.Center)
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = "#f27e74".color)
            )


        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(AppScreens.TaskDetails.route)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally){
            LazyColumn() {
                items(contentList.size) { index ->
                    TaskItem(item = contentList[index])
                }
            }
            Spacer(modifier = Modifier.size(100.dp))
        }
    }
}

@Composable
fun TaskItem(item: Task){
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ){

    }

}