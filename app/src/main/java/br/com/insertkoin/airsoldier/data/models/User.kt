package br.com.insertkoin.airsoldier.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.insertkoin.airsoldier.util.Constants.DATABASE_USER

@Entity(tableName = DATABASE_USER)
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val experience: Long,
    val level: Int,
    val picture: String,
    val tag: String = when (level) {
        1 -> "recruta"
        2 -> "Aspira"
        3 -> "Soldado"
        else -> "bugou"
    }
)
