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
    houseId: Int? = null
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var squareMeters by remember { mutableStateOf("") }
    var molina by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

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
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                errorMessage = if (it.isEmpty()) "El nombre es requerido" else ""
            },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        if (errorMessage.isNotEmpty()) {
            Text(errorMessage, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = squareMeters,
            onValueChange = {
                squareMeters = it
                errorMessage = if (it.isNotEmpty() && it.toDoubleOrNull() == null) "Debe ser un número válido" else ""
            },
            label = { Text("Metros Cuadrados") },
            modifier = Modifier.fillMaxWidth()
        )

        if (errorMessage.isNotEmpty()) {
            Text(errorMessage, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = molina,
            onValueChange = { molina = it },
            label = { Text("Molina") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (name.isBlank()) {
                    errorMessage = "El nombre es requerido"
                    return@Button
                }

                if (squareMeters.isNotEmpty() && squareMeters.toDoubleOrNull() == null) {
                    errorMessage = "Metros cuadrados debe ser un número válido"
                    return@Button
                }

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
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (houseId == null) "Agregar Casa" else "Actualizar Casa")
        }
    }
}
