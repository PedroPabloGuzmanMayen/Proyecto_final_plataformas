package com.example.proyectofinal.Interface

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.proyectofinal.Task.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Tus tareas", fontSize = 20.sp, textAlign = TextAlign.Center)
            },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = "#f27e74".color))


        }
    ){ innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(contentList.size) { index ->
                TaskItem(item = contentList[index])
            }
        }

    }
}

@Composable
fun TaskItem(item: Task){

}