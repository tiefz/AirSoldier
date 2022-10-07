package br.com.insertkoin.airsoldier.repository

import br.com.insertkoin.airsoldier.data.AirSoldierDao
import br.com.insertkoin.airsoldier.data.models.User
import javax.inject.Inject

class AirSoldierRepository @Inject constructor(private val dao: AirSoldierDao) {

    suspend fun insertUser(user: User) {
        dao.insertUser(user)
    }

    suspend fun updateUser(user: User) {
        dao.updateUser(user)
    }
}