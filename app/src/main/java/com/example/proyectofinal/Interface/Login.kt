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
    Proyecto_final_plataformas {
        LoginScreen()
    }
}

@Composable
fun LoginScreen() {
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
