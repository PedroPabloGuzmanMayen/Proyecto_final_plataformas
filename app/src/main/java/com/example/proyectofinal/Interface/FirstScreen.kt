package com.example.proyectofinal.Interface

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectofinal.Interface.TaskScreen.View.color

@Composable
fun firstScreen(navController: NavController) {

    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        Button(
            onClick = { navController.navigate("login") },
            modifier = Modifier.padding(10.dp),
            colors = ButtonDefaults.buttonColors("#fcb603".color)
        ) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.padding(10.dp).size(20.dp))
        Button(
            onClick = { navController.navigate("register") },
            modifier = Modifier.padding(10.dp),
            colors = ButtonDefaults.buttonColors("#fcb603".color)
        ) {
            Text(text = "Register")
        }




    }

}




