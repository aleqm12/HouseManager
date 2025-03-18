package commolina.ulatina.housemanageralejandroquesada.userinterfaz.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import commolina.ulatina.housemanageralejandroquesada.model.HouseAlejandro
import commolina.ulatina.housemanageralejandroquesada.ui.screens.HouseDetailScreen
import commolina.ulatina.housemanageralejandroquesada.ui.screens.HouseFormScreen
import commolina.ulatina.housemanageralejandroquesada.ui.screens.HouseListScreen
import commolina.ulatina.housemanageralejandroquesada.viewmodel.HouseViewModel

@Composable
fun AppNavGraph(viewModelStoreOwner: ViewModelStoreOwner = LocalViewModelStoreOwner.current!!) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "houseList" // Pantalla inicial
    ) {
        // Pantalla principal: Lista de casas
        composable("houseList") {
            val viewModel: HouseViewModel = hiltViewModel(viewModelStoreOwner)
            HouseListScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        // Pantalla para agregar o editar una casa
        composable(
            route = "houseForm/{houseJson}",
            arguments = listOf(
                navArgument("houseJson") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            // Recuperamos el objeto HouseAlejandro
            val json = backStackEntry.arguments?.getString("houseJson")
            val house = json?.let {
                try {
                    Gson().fromJson(it, HouseAlejandro::class.java)
                } catch (e: Exception) {
                    null // Manejo de errores si el JSON es inválido
                }
            }

            val viewModel: HouseViewModel = hiltViewModel(viewModelStoreOwner)
            HouseFormScreen(
                navController = navController,
                viewModel = viewModel,
                houseId = house?.id // Pasamos el ID de la casa (null si es nueva)
            )
        }

        // Pantalla para ver los detalles de una casa
        composable(
            route = "houseDetail/{houseJson}",
            arguments = listOf(
                navArgument("houseJson") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            // Recuperamos el objeto HouseAlejandro
            val json = backStackEntry.arguments?.getString("houseJson")
            val house = json?.let {
                try {
                    Gson().fromJson(it, HouseAlejandro::class.java)
                } catch (e: Exception) {
                    null // Manejo de errores si el JSON es inválido
                }
            }

            if (house != null) {
                val viewModel: HouseViewModel = hiltViewModel(viewModelStoreOwner)
                HouseDetailScreen(
                    navController = navController,
                    viewModel = viewModel,
                    houseId = house.id // Pasamos el ID de la casa
                )
            } else {
                // Manejo en caso de que la casa sea null (ej. navegar a una pantalla de error)
            }
        }
    }
}
