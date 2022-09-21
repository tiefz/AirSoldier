package br.com.insertkoin.airsoldier.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.insertkoin.airsoldier.util.Constants.DATABASE_ROUND
import java.time.LocalTime

@Entity(tableName = DATABASE_ROUND)
data class Round(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val time: LocalTime,
    val kills: Int,
    val isDead: Boolean,
    val isTeamWinner: Boolean,
    val loadOut: Int,
    val isFinished: Boolean
)
