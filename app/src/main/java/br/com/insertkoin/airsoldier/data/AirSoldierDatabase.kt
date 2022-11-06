package br.com.insertkoin.airsoldier.data

import android.content.Context
import androidx.room.*
import br.com.insertkoin.airsoldier.data.models.Game
import br.com.insertkoin.airsoldier.data.models.LoadOut
import br.com.insertkoin.airsoldier.data.models.Round
import br.com.insertkoin.airsoldier.data.models.User
import java.util.*

@Database(
    entities = [User::class, Game::class, LoadOut::class, Round::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AirSoldierDatabase : RoomDatabase() {

    abstract fun getDao(): AirSoldierDao

    companion object {

        @Volatile
        private var INSTANCE: AirSoldierDatabase? = null


        fun getUserInstance(context: Context): AirSoldierDatabase {

            synchronized(this) {

                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AirSoldierDatabase::class.java,
                        "airsoldier"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}