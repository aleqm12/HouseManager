package commolina.ulatina.housemanageralejandroquesada.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import commolina.ulatina.housemanageralejandroquesada.data.repository.HouseRepository
import commolina.ulatina.housemanageralejandroquesada.model.HouseAlejandro
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HouseViewModel @Inject constructor(
    private val repository: HouseRepository // Inyección de la dependencia HouseRepository
) : ViewModel() {

    // Exposición de todos los elementos como LiveData para que la UI pueda observar los cambios
    val allItems: LiveData<List<HouseAlejandro>> get() = repository.getAllItems()

    // Función para insertar una casa
    fun insert(houseAlejandro: HouseAlejandro) = viewModelScope.launch {
        repository.insert(houseAlejandro)
    }

    // Función para actualizar una casa
    fun update(houseAlejandro: HouseAlejandro) = viewModelScope.launch {
        repository.update(houseAlejandro)
    }

    // Función para eliminar un item específico
    fun deleteItem(houseAlejandro: HouseAlejandro) {
        viewModelScope.launch {
            repository.delete(houseAlejandro)
        }
    }

    // Función para eliminar todos los items
    fun deleteAllItems() = viewModelScope.launch {
        repository.deleteAllItems()
    }

    // Función para obtener una casa por ID como LiveData
    fun getItemById(houseId: Int): LiveData<HouseAlejandro?> {
        return repository.getItemById(houseId) // Devuelve LiveData directamente del repositorio
    }
}
