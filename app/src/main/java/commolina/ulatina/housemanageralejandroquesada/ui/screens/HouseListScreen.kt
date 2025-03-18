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
    navController: NavController,
    viewModel: HouseViewModel = hiltViewModel()
) {
    // Observa la lista de casas desde el ViewModel
    val houses by viewModel.allItems.observeAsState(emptyList())

    Scaffold(
        floatingActionButton = {
            // Botón flotante para agregar una nueva casa
            FloatingActionButton(onClick = { navController.navigate("houseForm/null") }) {
                Icon(Icons.Default.Add, contentDescription = "Add House")
            }
        }
    ) { padding ->
        // Lista de casas
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(houses) { house ->
                HouseItem(
                    house = house,
                    onHouseClick = {
                        // Serializa la casa a JSON y navega a la pantalla de edición
                        val houseJson = Gson().toJson(house)
                        navController.navigate("houseForm/$houseJson")
                    },
                    onDeleteClick = { viewModel.deleteItem(house) }
                )
            }
        }
    }
}

@Composable
fun HouseItem(
    house: HouseAlejandro,
    onHouseClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onHouseClick() }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = house.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "Square Meters: ${house.squaremeters}", style = MaterialTheme.typography.bodyMedium)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = onDeleteClick) {
                    Text("Delete")
                }
                Button(onClick = onHouseClick) {
                    Text("Edit")
                }
            }
        }
    }
}
