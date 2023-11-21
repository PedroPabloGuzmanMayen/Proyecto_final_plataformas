package com.example.proyectofinal.Interface

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyectofinal.Model.TaskModel
import com.example.proyectofinal.Navigation.AppScreens
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import com.example.proyectofinal.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navController: NavController, sharedViewModel: SharedViewModel, username: String?) {
    sharedViewModel.updateLists(username.orEmpty())
    val contentList = sharedViewModel.lists
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.YourLists), fontSize = 30.sp, textAlign = TextAlign.Center)
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = "#f27e74".color)
            )


        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("createList/${username}")
            }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally){

            LazyColumn() {
                items(contentList.size) { index ->
                    TaskItem(item = contentList[index], navController, username.orEmpty())
                }
            }
            Spacer(modifier = Modifier.size(100.dp))
        }
    }
}

@Composable
fun TaskItem(item: String, navController: NavController, username: String){
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clickable(onClick = { navController.navigate("ActivityList/${username}/${item}") })
    ){
        Text(item)
    }

}