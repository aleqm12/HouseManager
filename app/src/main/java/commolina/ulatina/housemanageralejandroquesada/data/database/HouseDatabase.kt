package commolina.ulatina.housemanageralejandroquesada.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import commolina.ulatina.housemanageralejandroquesada.data.database.interfaces.HouseDao
import commolina.ulatina.housemanageralejandroquesada.model.HouseAlejandro

// Anotación que define la base de datos de Room
@Database(entities = [HouseAlejandro::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    // Método abstracto que proporciona acceso al DAO
    abstract fun houseDao(): HouseDao

    companion object {
        // Volatile para asegurar que los cambios en INSTANCE sean visibles para todos los hilos
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Método para obtener una instancia de la base de datos
        fun getDatabase(context: Context): AppDatabase {
            // Retorna la instancia existente si no es nula
            return INSTANCE ?: synchronized(this) {
                // Si INSTANCE es nula, crea una nueva instancia
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "house_database" // Nombre de la base de datos
                ).build()
                // Guarda la nueva instancia en INSTANCE
                INSTANCE = instance
                instance
            }
        }
    }
}