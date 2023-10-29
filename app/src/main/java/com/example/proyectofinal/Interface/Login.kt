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

@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Alineado al centro lo màs posible ambas cajas
        Text(
            text = "User:",
            modifier = Modifier.padding(top = 16.dp)
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Usuario") },
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
