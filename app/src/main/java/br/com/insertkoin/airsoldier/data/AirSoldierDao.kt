package br.com.insertkoin.airsoldier.data

import androidx.room.*
import br.com.insertkoin.airsoldier.data.models.Game
import br.com.insertkoin.airsoldier.data.models.LoadOut
import br.com.insertkoin.airsoldier.data.models.Round
import br.com.insertkoin.airsoldier.data.models.User

@Dao
interface AirSoldierDao {

    @Insert
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * from user_table WHERE id = :key")
    suspend fun getUser(key: Int): User?

    @Query("DELETE FROM user_table")
    suspend fun clearUser()

    @Insert
    suspend fun insertGame(game: Game)

    @Update
    suspend fun updateGame(game: Game)

    @Query("SELECT * from game_table ORDER BY date DESC")
    suspend fun getGames(): List<Game>?

    @Query("SELECT * from game_table WHERE id = :key")
    suspend fun getGame(key: Int): Game?

    @Insert
    suspend fun insertRound(round: Round)

    @Update
    suspend fun updateRound(round: Round)

    @Query("SELECT * from round_table ORDER BY id DESC")
    suspend fun getRounds(): List<Round>?

    @Query("SELECT * from round_table WHERE id = :key")
    suspend fun getRound(key: Int): Round?

    @Query("DELETE from round_table")
    suspend fun clearRounds()

    @Delete
    suspend fun deleteRound(vararg round: Round)

    @Insert
    suspend fun insertLoadOut(loadOut: LoadOut)

    @Update
    suspend fun updateLoadOut(loadOut: LoadOut)

    @Query("SELECT * from loadout_table")
    suspend fun getLoadOuts(): List<LoadOut>?
}