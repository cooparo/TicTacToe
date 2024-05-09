package it.unipd.dei.esp2021.tictactoe.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import it.unipd.dei.esp2021.tictactoe.model.Game

@Database(
    entities = [Game::class],
    version = 2,
    exportSchema = false
)
abstract class GameDatabase : RoomDatabase() {

    abstract val dao: GameDao

    companion object {
        @Volatile
        private var INSTANCE: GameDatabase? = null

        fun getDatabase(context: Context): GameDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context = context.applicationContext,
                    GameDatabase::class.java,
                    "games"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }


        }
    }
}