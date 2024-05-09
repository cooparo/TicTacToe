package it.unipd.dei.esp2021.tictactoe.data

import it.unipd.dei.esp2021.tictactoe.model.Game
import kotlinx.coroutines.flow.Flow

class GameRepository(
    private val gameDao: GameDao
) {

    fun allGames(): Flow<List<Game>> = gameDao.getAll()
    fun allGamesOrderedByDate(): Flow<List<Game>> = gameDao.getGameOrderedByDate()

    suspend fun insert(game: Game) = if (game.result.isEnded()) gameDao.insert(game) else {
    }

    companion object {
        @Volatile
        private var INSTANCE: GameRepository? = null

        fun getInstance(gameDao: GameDao) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: GameRepository(gameDao).also { INSTANCE = it }
            }
    }
}