package com.example.proyectofinal.Interface.TaskScreen


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
import com.example.proyectofinal.Model.TaskModel
import com.example.proyectofinal.R
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter


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
                    Text(text = stringResource(id = R.string.Yourtasks), fontSize = 30.sp, textAlign = TextAlign.Center)
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
                    activityComponent(item = contentList[index], navController, username.toString(), listName.toString())
                }
            }
            Spacer(modifier = Modifier.size(100.dp))
        }
    }
}

@Composable
fun activityComponent(item: TaskModel, navController: NavController, username: String, listname: String){
    var alert by remember {mutableStateOf("")}
    val formatter = DateTimeFormatter.ofPattern("MMM dd yyyy")
    val timeformat = DateTimeFormatter.ofPattern("hh:mm a")
    val localDate = LocalDate.parse(item.date, formatter)
    val localtime = LocalTime.parse(item.time, timeformat)
    if(localDate.isBefore(LocalDate.now()) || (localtime.isBefore(LocalTime.now()) && localDate.isEqual(LocalDate.now()))){
        alert = "This task is overdue"
    }

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clickable(onClick={navController.navigate("deleteScreen/${username}/${listname}/${item.name}")})
    ){
        Column(){
            Text(text = item.name, fontSize = 20.sp, textAlign = TextAlign.Center)
            Text(text = item.date, fontSize = 20.sp, textAlign = TextAlign.Center)
            Text(text = item.time, fontSize = 20.sp, textAlign = TextAlign.Center)
            Text(alert, fontSize = 20.sp, textAlign = TextAlign.Center)
        }

    }

}