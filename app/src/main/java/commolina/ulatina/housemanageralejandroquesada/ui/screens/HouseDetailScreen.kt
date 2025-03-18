package commolina.ulatina.housemanageralejandroquesada.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import commolina.ulatina.housemanageralejandroquesada.viewmodel.HouseViewModel

@Composable
fun HouseDetailScreen(
    navController: NavController,
    viewModel: HouseViewModel = hiltViewModel(),
    houseId: Int
) {
    // Cargar los detalles de la casa
    val house by viewModel.getItemById(houseId).observeAsState(null)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (house != null) {
            Text(text = "Nombre: ${house!!.name}", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Descripción: ${house!!.description}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Metros Cuadrados: ${house!!.squaremeters}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Molina: ${house!!.molina}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate("houseForm/${house!!.id}") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Editar Casa")
            }
        } else {
            Text("Casa no encontrada", style = MaterialTheme.typography.titleLarge)
        }
    }
}
