class ToDoListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Proyecto_final_plataformas {
                // Definir la IU de inicio de sesión aquí
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ToDoScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewToDoScreen(){
    Proyecto_final_plataformas{
        ToDoScreen()
    }
}

@Composable
fun ToDoScreen(){
    // Lista mutable para almacenar las tareas-por-hacer
    val ToDoL = mutableStateListOf<String>()

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        // Tamaño para cada recuadro
        contentPadding = PaddingValue(all = 12.dp)
    ){
        ToDoL.forEach { todo -> Text(todo)}
    }
    // Seccion del boton
    Button(
        onClick = {ToDoL.add},
        modifier = Modifier
            .align(Alignment.BottomLeft)
            .padding(all = 12.dp)
    ){
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = "Boton add for any item"
        )
    }
}