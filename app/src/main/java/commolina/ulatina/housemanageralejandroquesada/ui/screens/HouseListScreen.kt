package commolina.ulatina.housemanageralejandroquesada.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import commolina.ulatina.housemanageralejandroquesada.model.HouseAlejandro
import commolina.ulatina.housemanageralejandroquesada.viewmodel.HouseViewModel
import com.google.gson.Gson

@Composable
fun HouseListScreen(
    navController: NavController, // Controlador de navegación
    viewModel: HouseViewModel = hiltViewModel() // ViewModel inyectado automáticamente
) {
    // Observa la lista de casas desde el ViewModel
    val houses by viewModel.allItems.observeAsState(emptyList()) // Lista de casas, inicializa con una lista vacía

    Scaffold(
        floatingActionButton = {
            // Botón flotante para agregar una nueva casa
            FloatingActionButton(onClick = { navController.navigate("houseForm/null") }) {
                Icon(Icons.Default.Add, contentDescription = "Add House") // Icono de agregar
            }
        }
    ) { padding -> // Padding proporcionado por Scaffold
        // Lista de casas
        LazyColumn(
            modifier = Modifier
                .fillMaxSize() // Llena todo el espacio disponible
                .padding(padding) // Aplica el padding proporcionado por Scaffold
        ) {
            // Itera sobre la lista de casas
            items(houses) { house ->
                // Componente para cada item de la casa
                HouseItem(
                    house = house, // Pasa la casa a la vista del item
                    onHouseClick = {
                        // Serializa la casa a JSON y navega a la pantalla de edición
                        val houseJson = Gson().toJson(house) // Convierte la casa a formato JSON
                        navController.navigate("houseForm/$houseJson") // Navega a la pantalla de formulario
                    },
                    onDeleteClick = { viewModel.deleteItem(house) } // Elimina la casa usando el ViewModel
                )
            }
        }
    }
}

@Composable
fun HouseItem(
    house: HouseAlejandro, // Objeto de la casa
    onHouseClick: () -> Unit, // Acción al hacer clic en la casa
    onDeleteClick: () -> Unit // Acción al hacer clic en eliminar
) {
    Card(
        modifier = Modifier
            .fillMaxWidth() // Llena todo el ancho disponible
            .padding(8.dp) // Agrega un padding de 8dp alrededor de la tarjeta
            .clickable { onHouseClick() } // Hace que la tarjeta sea clickeable
    ) {
        Column(
            modifier = Modifier.padding(16.dp) // Padding dentro de la tarjeta
        ) {
            // Muestra el nombre de la casa
            Text(text = house.name, style = MaterialTheme.typography.titleMedium)
            // Muestra los metros cuadrados de la casa
            Text(text = "Square Meters: ${house.squaremeters}", style = MaterialTheme.typography.bodyMedium)
            Row(
                modifier = Modifier.fillMaxWidth(), // Llena el ancho de la fila
                horizontalArrangement = Arrangement.SpaceBetween // Espaciado horizontal entre los botones
            ) {
                // Botón para eliminar la casa
                Button(onClick = onDeleteClick) {
                    Text("Delete") // Texto del botón
                }
                // Botón para editar la casa
                Button(onClick = onHouseClick) {
                    Text("Edit") // Texto del botón
                }
            }
        }
    }
}
