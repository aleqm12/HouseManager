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
    navController: NavController,
    viewModel: HouseViewModel = hiltViewModel(),
    houseId: Int? = null // ID de la casa para editar (opcional)
) {
    // Estado para los campos del formulario
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var squareMeters by remember { mutableStateOf("") }
    var molina by remember { mutableStateOf("") }

    // Obtener la casa si estamos en modo de edición
    val house by viewModel.getItemById(houseId ?: -1).observeAsState(null)

    LaunchedEffect(house) {
        house?.let {
            name = it.name
            description = it.description
            squareMeters = it.squaremeters.toString()
            molina = it.molina
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Campo para el nombre
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo para la descripción
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo para los metros cuadrados
        OutlinedTextField(
            value = squareMeters,
            onValueChange = { squareMeters = it },
            label = { Text("Metros Cuadrados") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo para Molina
        OutlinedTextField(
            value = molina,
            onValueChange = { molina = it },
            label = { Text("Molina") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para guardar
        Button(
            onClick = {
                val house = HouseAlejandro(
                    id = houseId ?: 0,
                    name = name,
                    description = description,
                    squaremeters = squareMeters.toDoubleOrNull() ?: 0.0,
                    molina = molina
                )
                if (houseId == null) {
                    viewModel.insert(house)
                } else {
                    viewModel.update(house)
                }
                navController.popBackStack() // Regresar a la pantalla anterior
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (houseId == null) "Agregar Casa" else "Actualizar Casa")
        }
    }
}
