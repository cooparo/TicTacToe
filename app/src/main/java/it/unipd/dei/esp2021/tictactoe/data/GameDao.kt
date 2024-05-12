package it.unipd.dei.esp2021.tictactoe.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import it.unipd.dei.esp2021.tictactoe.model.Game
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Insert
    suspend fun insertAll(vararg games: Game)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(game: Game)

    @Delete
    suspend fun delete(game: Game)

    @Query("SELECT * FROM games")
    fun getAll(): Flow<List<Game>>

    @Query("SELECT * FROM games ORDER BY date DESC LIMIT 20")
    fun getGameOrderedByDate(): Flow<List<Game>>
}