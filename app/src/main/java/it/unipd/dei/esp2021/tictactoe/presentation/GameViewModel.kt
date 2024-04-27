package it.unipd.dei.esp2021.tictactoe.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import it.unipd.dei.esp2021.tictactoe.domain.model.Box
import it.unipd.dei.esp2021.tictactoe.domain.model.Game
import it.unipd.dei.esp2021.tictactoe.domain.model.Result
import it.unipd.dei.esp2021.tictactoe.domain.model.Symbol
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {

    private val _gameState = MutableStateFlow(Game())
    val gameState: StateFlow<Game> = _gameState.asStateFlow()

    private val board: MutableLiveData<MutableList<MutableList<Box>>> by lazy {
        MutableLiveData<MutableList<MutableList<Box>>>()
    }

    fun onClickBox(box: Box) {
        val currentPlayer: Symbol = _gameState.value.currentPlayer
        val turn: Int = _gameState.value.turn
        val isEmpty: Boolean = box.symbol == Symbol.SYMBOL_EMPTY

        if (isEmpty && !_gameState.value.result.isEnded()) {

            board.value?.get(box.row)!!.get(box.column).symbol = currentPlayer

            _gameState.update { currentGame ->
                currentGame.copy(
                    currentPlayer = currentPlayer.next(),
                    turn = turn + 1,
                    result = checkResult()
                )
            }
        }
    }

    private fun checkResult(): Result {
        val XhasWon = symbolHasWon(Symbol.SYMBOL_CROSS)
        val OhasWon = symbolHasWon(Symbol.SYMBOL_NOUGHT)

        return if (isBoardEmpty()) Result.RESULT_NEW_GAME
        else if (XhasWon) Result.RESULT_PLAYER_X
        else if (OhasWon) Result.RESULT_PLAYER_O
        else if (isBoardFull()) Result.RESULT_DRAW
        else Result.RESULT_ONGOING
    }

    private fun symbolHasWon(symbol: Symbol): Boolean {
        if (symbol == Symbol.SYMBOL_EMPTY) throw IllegalArgumentException() // Empty symbol can't win obvs

        val board = this.board.value
        // Check all rows
//        for (row in this.board.value) {
//            if (row[0] == symbol && row[1] == symbol && row[2] == symbol) return true
//        }
        for (i in 0..2) {
            val row = board?.get(i)!!
            if (row[0].symbol == symbol && row[1].symbol == symbol && row[2].symbol == symbol) return true
        }

        // Check all columns
        for (i in 0..2) {
            if (board!![0][i].symbol == symbol && board[1][i].symbol == symbol && board[2][i].symbol == symbol) return true
        }

        // Check diagonals
        if (
            (board!![0][0].symbol == symbol && board[1][1].symbol == symbol && board[2][2].symbol == symbol) ||
            (board[0][2].symbol == symbol && board[1][1].symbol == symbol && board[2][0].symbol == symbol)
        ) return true

        return false
    }

    private fun isBoardEmpty(): Boolean {
        for (row in board.value!!) {
            for (box in row) {
                if (box.symbol != Symbol.SYMBOL_EMPTY) return false
            }
        }
        return true
    }

    private fun isBoardFull(): Boolean {
        for (row in board.value!!) {
            for (box in row) {
                if (box.symbol == Symbol.SYMBOL_EMPTY) return false
            }
        }
        return true
    }

}