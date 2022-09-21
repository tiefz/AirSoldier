package br.com.insertkoin.airsoldier.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.insertkoin.airsoldier.util.Constants.DATABASE_LOADOUT

@Entity(tableName = DATABASE_LOADOUT)
data class LoadOut(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val experience: Long,
    val level: Int
)
