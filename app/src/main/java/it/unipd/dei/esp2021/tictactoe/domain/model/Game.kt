package it.unipd.dei.esp2021.tictactoe.domain.model

import java.lang.Exception
import java.util.Date

/**
 * Inizia sempre la X
 */
class Game(
    private var board: Array<Array<Symbol>> = arrayOf(
        arrayOf(Symbol.SYMBOL_EMPTY, Symbol.SYMBOL_EMPTY, Symbol.SYMBOL_EMPTY),
        arrayOf(Symbol.SYMBOL_EMPTY, Symbol.SYMBOL_EMPTY, Symbol.SYMBOL_EMPTY),
        arrayOf(Symbol.SYMBOL_EMPTY, Symbol.SYMBOL_EMPTY, Symbol.SYMBOL_EMPTY),
    )

) {
    private var turn: Int = 0
    private val time: Long = Date().time
    var result: Result = Result.RESULT_NEW_GAME

    init {
        if (!isBoardEmpty()) {
            result = getResult()
            turn = countTurn()
        }
    }

    fun setBoardSymbol(row: Int, col: Int) {
        when(turn%2 ==0) {
            true -> board[row][col] = Symbol.SYMBOL_CROSS // Set X
            false -> board[row][col] = Symbol.SYMBOL_NOUGHT // Set O
        }
        turn++
    }

    fun getBoardSymbol(row: Int, col: Int): String {
        return when (board[row][col]) {
            Symbol.SYMBOL_CROSS -> "X"
            Symbol.SYMBOL_NOUGHT -> "O"
            Symbol.SYMBOL_EMPTY -> ""
        }
    }

    fun getResult(): Result {
        val XhasWon = symbolHasWon(Symbol.SYMBOL_CROSS)
        val OhasWon = symbolHasWon(Symbol.SYMBOL_NOUGHT)

        return if (isBoardEmpty()) Result.RESULT_NEW_GAME
        else if (XhasWon) Result.RESULT_PLAYER_X
        else if (OhasWon) Result.RESULT_PLAYER_O
        else if (isBoardFull()) Result.RESULT_DRAW
        else Result.RESULT_ONGOING
    }

    private fun countTurn(): Int {
        var counter = 0
        for (row in board) {
            for (elem in row) {
                if (elem != Symbol.SYMBOL_EMPTY) counter++
            }
        }
        return counter
    }

    private fun isBoardEmpty(): Boolean {
        for (row in board) {
            for (elem in row) {
                if (elem != Symbol.SYMBOL_EMPTY) return false
            }
        }
        return true
    }

    private fun isBoardFull(): Boolean {
        for (row in board) {
            for (elem in row) {
                if (elem == Symbol.SYMBOL_EMPTY) return false
            }
        }
        return true
    }

    private fun symbolHasWon(symbol: Symbol): Boolean {
        if (symbol == Symbol.SYMBOL_EMPTY) throw IllegalArgumentException() // Empty symbol can't win obvs

        // Check all rows
        for (row in this.board) {
            if(row[0]==symbol && row[1]==symbol && row[2]==symbol) return true
        }

        // Check all columns
        for (i in 0..2) {
            if ( board[0][i]==symbol && board[1][i]==symbol && board[2][i]==symbol) return true
        }

        // Check diagonals
        if (
            (board[0][0]==symbol && board[1][1]==symbol && board[2][2]==symbol) ||
            ( board[0][2]==symbol && board[1][1]==symbol && board[2][0]==symbol)
        ) return true

        return false
    }


}