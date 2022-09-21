package br.com.insertkoin.airsoldier.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.insertkoin.airsoldier.util.Constants.DATABASE_GAME
import java.time.LocalTime
import java.util.*

@Entity(tableName = DATABASE_GAME)
data class Game(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val date: Date,
    val time: LocalTime,
    val kills: Int,
    val deaths: Int,
    val isFinished: Boolean
)
