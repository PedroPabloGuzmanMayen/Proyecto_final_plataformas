class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Proyecto_final_plataformas {
                // Definir la IU de inicio de sesión aquí
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    Lab5_ComposeTheme {
        LoginScreen()
    }
}

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.proyectofinal.Task.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Usuario") },
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
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Contraseña") },
            keyboardType = KeyboardType.Password,
        )

        // Botón de log in
        Button(
            modifier = Modifier.padding(top = 16.dp),
            onClick = { /* Acción del botón */ }
        ) {
            Text("Iniciar sesión")
        }
    }
}
