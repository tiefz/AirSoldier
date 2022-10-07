package br.com.insertkoin.airsoldier.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.insertkoin.airsoldier.util.Constants.DATABASE_GAME
import java.util.*

@Entity(tableName = DATABASE_GAME)
data class Game(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val date: Date,
    @ColumnInfo(name = "start_time_milli")
    val startTimeMilli: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "end_time_milli")
    var endTimeMilli: Long = startTimeMilli,
    val kills: Int,
    val deaths: Int,
    val isFinished: Boolean
)