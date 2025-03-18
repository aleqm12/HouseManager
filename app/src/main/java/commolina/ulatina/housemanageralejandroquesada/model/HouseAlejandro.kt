package commolina.ulatina.housemanageralejandroquesada.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

// Define la entidad de la base de datos llamada "houses"
@Entity(tableName = "houses")
data class HouseAlejandro (

    // Clave primaria que se generará automáticamente
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    // Nombre de la casa, con valor predeterminado como cadena vacía
    val name: String = "",

    // Descripción de la casa, también con valor predeterminado como cadena vacía
    val description: String = "",

    // Área de la casa en metros cuadrados (no tiene valor predeterminado)
    val squaremeters: Double,

    // Campo adicional, posiblemente relacionado con "Molina"
    val molina: String

): Serializable // Permite que la instancia de esta clase sea serializable
