package br.com.insertkoin.airsoldier.di

import android.app.Application
import br.com.insertkoin.airsoldier.data.AirSoldierDao
import br.com.insertkoin.airsoldier.data.AirSoldierDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AirSoldierModule {

    @Singleton
    @Provides
    fun getDatabase(context: Application): AirSoldierDatabase {
        return AirSoldierDatabase.getUserInstance(context)
    }

    @Singleton
    @Provides
    fun getDao(usersDatabase: AirSoldierDatabase): AirSoldierDao {
        return usersDatabase.getDao()
    }
}