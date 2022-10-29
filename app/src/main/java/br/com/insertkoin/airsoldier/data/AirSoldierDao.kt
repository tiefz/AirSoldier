package br.com.insertkoin.airsoldier.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.insertkoin.airsoldier.data.models.Game
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

    @Query("SELECT * from game_table ORDER BY date DESC")
    suspend fun getGames(): List<Game>?
}