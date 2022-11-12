package br.com.insertkoin.airsoldier.repository

import br.com.insertkoin.airsoldier.data.AirSoldierDao
import br.com.insertkoin.airsoldier.data.models.Game
import br.com.insertkoin.airsoldier.data.models.LoadOut
import br.com.insertkoin.airsoldier.data.models.Round
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

    suspend fun updateGame(game: Game) {
        dao.updateGame(game)
    }

    suspend fun getGames(): List<Game>? {
        return dao.getGames()
    }

    suspend fun getGame(gameId: Int): Game? {
        return dao.getGame(gameId)
    }

    suspend fun insertRound(round: Round) {
        dao.insertRound(round)
    }

    suspend fun updateRound(round: Round) {
        dao.updateRound(round)
    }

    suspend fun getRounds(): List<Round>? {
        return dao.getRounds()
    }

    suspend fun getRound(roundId: Int): Round? {
        return dao.getRound(roundId)
    }

    suspend fun clearRounds() {
        dao.clearRounds()
    }

    suspend fun deleteRound(round: Round) {
        dao.deleteRound(round)
    }

    suspend fun insertLoadOut(loadOut: LoadOut) {
        dao.insertLoadOut(loadOut)
    }

    suspend fun updateLoadOut(loadOut: LoadOut) {
        dao.updateLoadOut(loadOut)
    }

    suspend fun getLoadOuts(): List<LoadOut>? {
        return dao.getLoadOuts()
    }
}