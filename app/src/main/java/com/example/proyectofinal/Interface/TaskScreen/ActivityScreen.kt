package com.example.proyectofinal.Interface.TaskScreen


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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectofinal.Model.TaskModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityScreen(navController: NavController, sharedViewModel: ActivityViewModel, username: String?, listName: String?) {
    LaunchedEffect(Unit){
        sharedViewModel.getActivities(username.orEmpty(), listName.orEmpty())
    }

    val contentList = sharedViewModel.contentList
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Tus tareas", fontSize = 20.sp, textAlign = TextAlign.Center)
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = "#f27e74".color),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )


        },

        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("TaskDetails/${username}/${listName}")
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally){

            LazyColumn() {
                items(contentList.size) { index ->
                    activityComponent(item = contentList[index], navController)
                }
            }
            Spacer(modifier = Modifier.size(100.dp))
        }
    }
}

@Composable
fun activityComponent(item: TaskModel, navController: NavController){
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ){
        Text(item.name)
    }

}