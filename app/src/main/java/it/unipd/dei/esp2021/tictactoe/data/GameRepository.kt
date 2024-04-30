package it.unipd.dei.esp2021.tictactoe.data

import it.unipd.dei.esp2021.tictactoe.model.Game

class GameRepository(
    private val gameDao: GameDao
) {

    fun getGames() = gameDao.getGameOrderedByDate()

    suspend fun addGame(game: Game) = gameDao.insertGame(game)

    companion object {
        @Volatile
        private var INSTANCE: GameRepository? = null

        fun getInstance(gameDao: GameDao) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: GameRepository(gameDao).also { INSTANCE = it }
            }
    }
}