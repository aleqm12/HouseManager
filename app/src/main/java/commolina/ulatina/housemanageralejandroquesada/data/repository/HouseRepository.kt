package commolina.ulatina.housemanageralejandroquesada.data.repository

import javax.inject.Inject
import androidx.lifecycle.LiveData
import commolina.ulatina.housemanageralejandroquesada.data.database.interfaces.HouseDao
import commolina.ulatina.housemanageralejandroquesada.model.HouseAlejandro

class HouseRepository @Inject constructor(private val houseDao: HouseDao) {

    // Obtiene todos los elementos (casas) de la base de datos como LiveData
    fun getAllItems(): LiveData<List<HouseAlejandro>> {
        return houseDao.getAllItems()
    }

    // Inserta una nueva casa en la base de datos
    suspend fun insert(houseAlejandro: HouseAlejandro) {
        houseDao.insert(houseAlejandro)
    }

    // Actualiza una casa existente en la base de datos
    suspend fun update(houseAlejandro: HouseAlejandro) {
        houseDao.update(houseAlejandro)
    }

    // Elimina todas las casas de la base de datos
    suspend fun deleteAllItems() {
        houseDao.deleteAllItems()
    }

    // Elimina una casa específica de la base de datos
    suspend fun delete(houseAlejandro: HouseAlejandro) {
        houseDao.delete(houseAlejandro)
    }

    // Obtiene una casa por ID como LiveData
    fun getItemById(houseId: Int): LiveData<HouseAlejandro?> {
        return houseDao.getItemById(houseId) // Asegúrate de que esta función esté en tu DAO
    }
}
