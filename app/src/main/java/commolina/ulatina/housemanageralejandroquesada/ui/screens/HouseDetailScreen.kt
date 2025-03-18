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
    navController: NavController, // Controlador de navegación para manejar la navegación
    viewModel: HouseViewModel = hiltViewModel(), // ViewModel de la casa, inyectado automáticamente
    houseId: Int // ID de la casa para obtener sus detalles
) {
    // Cargar los detalles de la casa usando el ViewModel
    val house by viewModel.getItemById(houseId).observeAsState(null) // Observa el estado del objeto house

    Column(
        modifier = Modifier
            .fillMaxSize() // Hace que la columna llene todo el espacio disponible
            .padding(16.dp) // Agrega un padding de 16dp alrededor de la columna
    ) {
        if (house != null) { // Verifica si se encontraron los detalles de la casa
            Text(text = "Nombre: ${house!!.name}", style = MaterialTheme.typography.titleLarge) // Muestra el nombre de la casa
            Spacer(modifier = Modifier.height(8.dp)) // Espacio entre elementos
            Text(text = "Descripción: ${house!!.description}", style = MaterialTheme.typography.bodyLarge) // Muestra la descripción
            Spacer(modifier = Modifier.height(8.dp)) // Espacio entre elementos
            Text(text = "Metros Cuadrados: ${house!!.squaremeters}", style = MaterialTheme.typography.bodyLarge) // Muestra los metros cuadrados
            Spacer(modifier = Modifier.height(8.dp)) // Espacio entre elementos
            Text(text = "Molina: ${house!!.molina}", style = MaterialTheme.typography.bodyLarge) // Muestra la información de Molina
            Spacer(modifier = Modifier.height(16.dp)) // Espacio entre elementos
            Button(
                onClick = { navController.navigate("houseForm/${house!!.id}") }, // Navega a la pantalla de edición de casa
                modifier = Modifier.fillMaxWidth() // Botón que llena todo el ancho disponible
            ) {
                Text("Editar Casa") // Texto del botón
            }
        } else {
            Text("Casa no encontrada", style = MaterialTheme.typography.titleLarge) // Mensaje si la casa no fue encontrada
        }
    }
}
