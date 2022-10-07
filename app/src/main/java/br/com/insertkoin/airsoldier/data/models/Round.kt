package br.com.insertkoin.airsoldier.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.insertkoin.airsoldier.util.Constants.DATABASE_ROUND

@Entity(tableName = DATABASE_ROUND)
data class Round(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "start_time_milli")
    val startTimeMilli: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "end_time_milli")
    var endTimeMilli: Long = startTimeMilli,
    val kills: Int,
    val isDead: Boolean,
    val isTeamWinner: Boolean,
    val loadOut: Int,
    val isFinished: Boolean
)
