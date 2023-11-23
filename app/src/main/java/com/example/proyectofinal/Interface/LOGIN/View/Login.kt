import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyectofinal.Interface.TaskScreen.View.color
import com.example.proyectofinal.Interface.LOGIN.ViewModel.LoginViewModel
import com.example.proyectofinal.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController){
    val context = LocalContext.current
    val viewmodel: LoginViewModel = viewModel()
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var alert by remember {mutableStateOf("")}
    var userList by remember { mutableStateOf<List<String>>(emptyList()) }
    var passwordList by remember { mutableStateOf<List<String>>(emptyList()) }

    LaunchedEffect(key1 = true) {
        userList = viewmodel.usersList()
        passwordList = viewmodel.passwordsList()
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Alineado al centro lo màs posible ambas cajas
        // Seccion de usuario
       Image(
           painter = painterResource(id = R.drawable.lightbulb),
           contentDescription = "Icono de usuario"
           // Se utilizarà una imagen que android studio trae por defecto
       )
        Text(
            stringResource(id = R.string.User),
            modifier = Modifier.padding(top = 16.dp)
        )
        TextField(
            value = name,
            onValueChange = { name = it },

            label = { Text(stringResource(id = R.string.User)) },
        )
        // Seccion contraseña
        Text(
            text = stringResource(id = R.string.Password),
            modifier = Modifier.padding(top = 16.dp)
        )
        TextField(
            value = password,
            onValueChange = { password = it },

            label = { Text(stringResource(id = R.string.Password)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )


        // Botón de log in
        Button(
            modifier = Modifier.padding(top = 16.dp),
            onClick = {
                if (userList.contains(name)) {

                    val index = userList.indexOf(name)
                    if (passwordList[userList.indexOf(name)] == password){
                        navController.navigate("Home/${name}")
                    }
                    else {

                        alert = context.resources.getString(R.string.Alert1)
                    }
                } else {

                    alert = context.resources.getString(R.string.Alert2)
                }

            },
            colors = ButtonDefaults.buttonColors("#fcb603".color)
        ) {
            Text(stringResource(id = R.string.Login), color = Color.Black)
        }


        Text(alert, color = Color.Red)


    }
}
