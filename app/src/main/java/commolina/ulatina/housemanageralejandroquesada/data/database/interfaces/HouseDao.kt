package commolina.ulatina.housemanageralejandroquesada.data.database.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import commolina.ulatina.housemanageralejandroquesada.model.HouseAlejandro

// Interfaz DAO (Data Access Object) para manejar operaciones de base de datos relacionadas con HouseAlejandro
@Dao
interface HouseDao {

    // Inserta una nueva casa en la base de datos
    @Insert
    suspend fun insert(houseAlejandro: HouseAlejandro)

    // Actualiza una casa existente en la base de datos
    @Update
    suspend fun update(houseAlejandro: HouseAlejandro)

    // Consulta para obtener todas las casas en la base de datos
    @Query("SELECT * FROM houses")
    fun getAllItems(): LiveData<List<HouseAlejandro>>

    // Elimina todas las casas de la base de datos
    @Query("DELETE FROM houses")
    suspend fun deleteAllItems()

    // Elimina una casa específica de la base de datos
    @Delete
    suspend fun delete(houseAlejandro: HouseAlejandro)

    // Obtiene una casa por ID
    @Query("SELECT * FROM houses WHERE id = :houseId LIMIT 1")
    suspend fun getItemById(houseId: Int): HouseAlejandro?


}