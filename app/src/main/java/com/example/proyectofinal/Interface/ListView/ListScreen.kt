package com.example.proyectofinal.Interface.ListView

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import com.example.proyectofinal.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navController: NavController, sharedViewModel: ListViewModel, username: String?) {
    LaunchedEffect(Unit){
        sharedViewModel.updateLists(username.orEmpty())
    }

    val contentList = sharedViewModel.lists
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.YourLists), fontSize = 30.sp, textAlign = TextAlign.Center)
                },

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

            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
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
        Row(){
            Image(painter = rememberVectorPainter(image = Icons.Filled.AddCircle), contentDescription = "Profile photo", alignment = Alignment.TopStart)
            Text(text = item, modifier = Modifier.padding(10.dp), fontSize = 20.sp, textAlign = TextAlign.End)
        }
    }

}


