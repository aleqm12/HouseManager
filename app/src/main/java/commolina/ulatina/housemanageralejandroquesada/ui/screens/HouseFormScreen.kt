package commolina.ulatina.housemanageralejandroquesada.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import commolina.ulatina.housemanageralejandroquesada.model.HouseAlejandro
import commolina.ulatina.housemanageralejandroquesada.viewmodel.HouseViewModel

@Composable
fun HouseFormScreen(
    navController: NavController, // Controlador de navegación
    viewModel: HouseViewModel = hiltViewModel(), // ViewModel de la casa
    houseId: Int? = null // ID de la casa para editar (opcional)
) {
    // Estado para los campos del formulario
    var name by remember { mutableStateOf("") } // Campo para el nombre
    var description by remember { mutableStateOf("") } // Campo para la descripción
    var squareMeters by remember { mutableStateOf("") } // Campo para los metros cuadrados
    var molina by remember { mutableStateOf("") } // Campo para Molina
    var errorMessage by remember { mutableStateOf("") } // Estado para el mensaje de error

    // Obtener la casa si estamos en modo de edición
    val house by viewModel.getItemById(houseId ?: -1).observeAsState(null)

    // Efecto que se ejecuta cuando se obtiene la casa
    LaunchedEffect(house) {
        house?.let {
            name = it.name // Asigna el nombre de la casa al campo
            description = it.description // Asigna la descripción al campo
            squareMeters = it.squaremeters.toString() // Asigna los metros cuadrados al campo
            molina = it.molina // Asigna el valor de Molina al campo
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize() // Llena todo el espacio disponible
            .padding(16.dp) // Agrega un padding de 16dp
    ) {
        // Campo para el nombre
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it // Actualiza el estado del nombre
                errorMessage = if (it.isEmpty()) "El nombre es requerido" else ""
            },
            label = { Text("Nombre") }, // Etiqueta del campo
            modifier = Modifier.fillMaxWidth() // Ancho completo
        )

        if (errorMessage.isNotEmpty()) {
            Text(errorMessage, color = MaterialTheme.colorScheme.error) // Mensaje de error
        }

        Spacer(modifier = Modifier.height(8.dp)) // Espacio entre elementos

        // Campo para la descripción
        OutlinedTextField(
            value = description,
            onValueChange = { description = it }, // Actualiza la descripción
            label = { Text("Descripción") }, // Etiqueta del campo
            modifier = Modifier.fillMaxWidth() // Ancho completo
        )

        Spacer(modifier = Modifier.height(8.dp)) // Espacio entre elementos

        // Campo para los metros cuadrados
        OutlinedTextField(
            value = squareMeters,
            onValueChange = {
                squareMeters = it // Actualiza los metros cuadrados
                errorMessage = if (it.isNotEmpty() && it.toDoubleOrNull() == null) "Debe ser un número válido" else ""
            },
            label = { Text("Metros Cuadrados") }, // Etiqueta del campo
            modifier = Modifier.fillMaxWidth() // Ancho completo
        )

        if (errorMessage.isNotEmpty()) {
            Text(errorMessage, color = MaterialTheme.colorScheme.error) // Mensaje de error
        }

        Spacer(modifier = Modifier.height(8.dp)) // Espacio entre elementos

        // Campo para Molina
        OutlinedTextField(
            value = molina,
            onValueChange = { molina = it }, // Actualiza el estado de Molina
            label = { Text("Molina") }, // Etiqueta del campo
            modifier = Modifier.fillMaxWidth() // Ancho completo
        )

        Spacer(modifier = Modifier.height(16.dp)) // Espacio entre elementos

        // Botón para guardar
        Button(
            onClick = {
                // Validación antes de guardar
                if (name.isBlank()) {
                    errorMessage = "El nombre es requerido"
                    return@Button
                }

                if (squareMeters.isNotEmpty() && squareMeters.toDoubleOrNull() == null) {
                    errorMessage = "Metros cuadrados debe ser un número válido"
                    return@Button
                }

                // Crea un objeto HouseAlejandro con los valores del formulario
                val house = HouseAlejandro(
                    id = houseId ?: 0, // Si houseId es nulo, asigna 0
                    name = name,
                    description = description,
                    squaremeters = squareMeters.toDoubleOrNull() ?: 0.0, // Convierte a Double
                    molina = molina
                )

                // Inserta o actualiza la casa en el ViewModel
                if (houseId == null) {
                    viewModel.insert(house) // Inserta nueva casa
                } else {
                    viewModel.update(house) // Actualiza casa existente
                }
                navController.popBackStack() // Regresa a la pantalla anterior
            },
            modifier = Modifier.fillMaxWidth() // Ancho completo
        ) {
            Text(if (houseId == null) "Agregar Casa" else "Actualizar Casa") // Texto del botón
        }
    }
}

