class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab5_ComposeTheme {
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
        // Vista previa de la IU de inicio de sesión
        LoginScreen()
    }
}

@Composable
fun LoginScreen() {
    // Define la interfaz de usuario de inicio de sesión aquí
    // Puedes usar Column, TextField, Button, etc. según tus necesidades
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Agrega aquí tus elementos de inicio de sesión (TextField, Button, etc.)
    }
}
