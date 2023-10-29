package com.example.proyectofinal.Interface

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField

import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

import androidx.compose.ui.unit.sp

import androidx.navigation.NavController
import com.example.proyectofinal.R
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import com.vanpra.composematerialdialogs.title
import io.github.boguszpawlowski.composecalendar.kotlinxDateTime.now
import java.time.LocalDate
import java.time.LocalTime

import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun TaskScreen(navController: NavController){
    var name by remember { mutableStateOf("") }
    var pickedDate by remember{ mutableStateOf(LocalDate.now()) }
    var pickedHour by remember{ mutableStateOf(LocalTime.now()) }
    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter.ofPattern("MMM dd yyyy").format(pickedDate)
        }
    }
    val formattedTime by remember {
        derivedStateOf {
            DateTimeFormatter.ofPattern("hh:mm").format(pickedHour)
        }
    }
    val dateDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Crear tarea", fontSize = 20.sp, textAlign = TextAlign.Center)
            },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Magenta),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                })


        }
    ){ innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){

            TextField(value = name, onValueChange = { name = it }, label = { Text(text = "Nombre de la tarea") })
            Spacer(modifier = Modifier.size(30.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = { dateDialogState.show() }) {
                   Text("Selecciona la fecha")
                }
                Text(text = formattedDate, modifier = Modifier.padding(10.dp))
            }
            Button(onClick = { dateDialogState.show() },
            colors = ButtonDefaults.buttonColors(R.color.bluegreen)) {
                Text(text = "Selecciona la fecha")
            }
            Button(onClick = { timeDialogState.show() }) {
                Text(text = "Selecciona la hora")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Guardar")
            }
        }

    }
    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton("Ok"){

            }
            negativeButton("Cancel")
        }
    ) {
        title("Selecciona la fecha")
        datepicker(pickedDate) { pickedDate = it }
    }


}


fun content(){

}

