package com.example.proyectofinal.Interface.TaskScreen.View



import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.compose.foundation.layout.Arrangement

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


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField

import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource


import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

import androidx.compose.ui.unit.sp

import androidx.navigation.NavController
import com.example.proyectofinal.Interface.NotificacionProgramada
import com.example.proyectofinal.Interface.NotificacionProgramada.Companion.EXTRA_TASK_NAME
import com.example.proyectofinal.Interface.TaskScreen.ViewModel.ActivityViewModel
import com.example.proyectofinal.Model.TaskModel
import com.example.proyectofinal.R

import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import com.vanpra.composematerialdialogs.title


import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun TaskScreen(navController: NavController, sharedViewModel: ActivityViewModel, username: String?, listName: String?){

    val context = LocalContext.current
    crearCanalNotificaciones("CanalAlarmas", context)
    var alert by remember {mutableStateOf("")}
    var name by remember { mutableStateOf("") }
    var pickedDate by remember{ mutableStateOf(LocalDate.now()) }
    var pickedHour by remember{ mutableStateOf(LocalTime.now()) }
    val contentList = remember { mutableStateListOf<TaskModel>() }
    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter.ofPattern("MMM dd yyyy").format(pickedDate)
        }
    }
    val formattedTime by remember {
        derivedStateOf {
            DateTimeFormatter.ofPattern("hh:mm a").format(pickedHour)
        }
    }
    val dateDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(R.string.CreateTask), fontSize = 30.sp, textAlign = TextAlign.Center)
            },

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
            TextField(value = name, onValueChange = { name = it }, label = { Text(stringResource(id = R.string.taskname)) })
            Spacer(modifier = Modifier.size(30.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = { dateDialogState.show() },
                colors = ButtonDefaults.buttonColors("#03fc77".color)) {
                   Text(stringResource(id = R.string.date))
                }
                Text(text = formattedDate, modifier = Modifier.padding(10.dp))
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = { timeDialogState.show() },
                    colors = ButtonDefaults.buttonColors("#03fc77".color)) {
                    Text(stringResource(id = R.string.time))
                }
                Text(text = formattedTime, modifier = Modifier.padding(10.dp))
            }
            Spacer(modifier = Modifier.size(30.dp))

            Button(onClick = {


                if (pickedDate.isBefore(LocalDate.now()) || (pickedDate.isEqual(LocalDate.now()) && pickedHour.isBefore(LocalTime.now()))){
                    alert = context.resources.getString(R.string.cannot)
                }
                else{
                    val newTask = TaskModel(name, formattedDate, formattedTime)

                    sharedViewModel.contentList.add(newTask)
                    sharedViewModel.addActivity(username.orEmpty(), listName.orEmpty(), newTask)
                    val userDateTime = LocalDateTime.of(pickedDate, pickedHour)
                    notificacionProgramada(context, userDateTime, name)
                    navController.popBackStack()

                }

            },
                colors = ButtonDefaults.buttonColors("#c92012".color)) {
                Text(text = stringResource(id = R.string.Save))
            }
            Text(alert, color = Color.Red)
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
        title(stringResource(id = R.string.Selectdate))
        datepicker(pickedDate) { pickedDate = it }
    }
    MaterialDialog(
        dialogState = timeDialogState,
        buttons = {
            positiveButton("Ok"){

            }
            negativeButton(stringResource(id = R.string.Cancel))
        }
    ) {
        title(stringResource(id = R.string.SelectTime))
        timepicker(pickedHour) { pickedHour = it }
    }


}


val String.color
    get() = Color(android.graphics.Color.parseColor(this))

@Composable
fun Notifiacaciones(){
    val context = LocalContext.current
    val id_canal = "CanalAlarmas"
    val idNotificacion = 0
    LaunchedEffect(Unit){
        crearCanalNotificaciones(id_canal, context)
    }

}

fun crearCanalNotificaciones(idCanal: String, context: Context){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        val nombre = "CanalAlarmas"
        val descripcion = "Canal para las alarmas"
        val importancia = NotificationManager.IMPORTANCE_HIGH
        val canal = NotificationChannel(idCanal, nombre, importancia).apply{
            description = descripcion
        }
        val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(canal)


    }
}

fun notificacionProgramada(context: Context, dateTime: LocalDateTime, name: String){
    val intent = Intent(context, NotificacionProgramada::class.java).apply {
        putExtra(EXTRA_TASK_NAME, name)
    }
    val requestCode = System.currentTimeMillis().toInt()
    val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT )
    var alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val triggerAtMillis = dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

    alarmManager.setExact(
        AlarmManager.RTC_WAKEUP,
        triggerAtMillis,
        pendingIntent
    )
}