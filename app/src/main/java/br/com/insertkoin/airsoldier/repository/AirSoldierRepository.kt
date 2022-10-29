package br.com.insertkoin.airsoldier.repository

import br.com.insertkoin.airsoldier.data.AirSoldierDao
import br.com.insertkoin.airsoldier.data.models.Game
import br.com.insertkoin.airsoldier.data.models.User
import javax.inject.Inject

class AirSoldierRepository @Inject constructor(private val dao: AirSoldierDao) {

    suspend fun insertUser(user: User) {
        dao.insertUser(user)
    }

    suspend fun updateUser(user: User) {
        dao.updateUser(user)
    }

    suspend fun getUser(): User? {
        return dao.getUser(1)
    }

    suspend fun insertGame(game: Game) {
        dao.insertGame(game)
    }

    suspend fun getGames(): List<Game>? {
        return dao.getGames()
    }
}