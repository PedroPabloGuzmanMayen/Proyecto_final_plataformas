import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyectofinal.Interface.color
import com.example.proyectofinal.LoginViewModel
import com.example.proyectofinal.Model.User
import com.example.proyectofinal.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController){
    val viewmodel: LoginViewModel = viewModel()
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var user: String? = "dalas"
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
           modifier = Modifier.padding(8.dp),
           // Se utilizarà una imagen que android studio trae por defecto
           imageVector = Icons.Default.AccountCircle,
           contentDescription = "Icono de user"
       )
        Text(
            text = "User:",
            modifier = Modifier.padding(top = 16.dp)
        )
        TextField(
            value = name,
            onValueChange = { name = it },

            label = { Text(stringResource(id = R.string.User)) },
        )
        // Seccion contraseña
        Image(
            modifier = Modifier.padding(8.dp),
            // Nuevamente se utilizarà una imagen por defecto de android studio
            imageVector = Icons.Default.Lock,
            contentDescription = "Icono de password"
        )
        Text(
            text = "Password:",
            modifier = Modifier.padding(top = 16.dp)
        )
        TextField(
            value = password,
            onValueChange = { password = it },

            label = { Text("Contraseña") },
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
                        alert = "Contraseña incorrecta"
                    }
                } else {
                    alert = "Usuario no registrado"
                }

            },
            colors = ButtonDefaults.buttonColors(Color.Yellow)
        ) {
            Text("Iniciar sesión")
        }
        Text(alert)
    }
}



