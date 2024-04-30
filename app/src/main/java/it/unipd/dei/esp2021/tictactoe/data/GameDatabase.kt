package it.unipd.dei.esp2021.tictactoe.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import it.unipd.dei.esp2021.tictactoe.model.Game

@Database(
    entities = [Game::class],
    version = 1,
    exportSchema = false
)
abstract class GameDatabase : RoomDatabase() {

    abstract val dao: GameDao

    companion object {
        @Volatile
        private var INSTANCE: GameDatabase? = null

        fun getInstance(context: Context): GameDatabase {
            val temp = INSTANCE
            if (temp != null) return temp

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context = context.applicationContext,
                    GameDatabase::class.java,
                    "games"
                ).build()
                INSTANCE = instance
                return instance
            }


        }
    }
}