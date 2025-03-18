package commolina.ulatina.housemanageralejandroquesada.di

import android.content.Context
import commolina.ulatina.housemanageralejandroquesada.data.repository.HouseRepository
import commolina.ulatina.housemanageralejandroquesada.data.database.AppDatabase
import commolina.ulatina.housemanageralejandroquesada.data.database.interfaces.HouseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


// Módulo Dagger para proporcionar dependencias
@Module
@InstallIn(SingletonComponent::class) // Indica que este módulo estará disponible en el ciclo de vida de la aplicación
object AppModule {

    // Proporciona una instancia de AppDatabase como un singleton
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    // Proporciona una instancia de HouseDao a partir de AppDatabase
    @Provides
    @Singleton
    fun provideHouseDao(appDatabase: AppDatabase): HouseDao {
        return appDatabase.houseDao()
    }

    // Proporciona una instancia de HouseRepository a partir de HouseDao
    @Provides
    @Singleton
    fun provideHouseRepository(houseDao: HouseDao): HouseRepository {
        return HouseRepository(houseDao)
    }
}