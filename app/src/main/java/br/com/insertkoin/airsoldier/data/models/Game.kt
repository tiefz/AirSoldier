package br.com.insertkoin.airsoldier.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.insertkoin.airsoldier.util.Constants.DATABASE_GAME
import java.util.*

@Entity(tableName = DATABASE_GAME)
data class Game(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val name: String,
    val date: Date,
    val kills: Int,
    val deaths: Int,
    var isFinished: Boolean
)