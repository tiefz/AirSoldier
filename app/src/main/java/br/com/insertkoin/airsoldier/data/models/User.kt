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
    val avatar: Int
)
