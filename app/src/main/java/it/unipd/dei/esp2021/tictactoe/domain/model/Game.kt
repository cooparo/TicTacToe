package it.unipd.dei.esp2021.tictactoe.domain.model

import java.util.Date


data class Game(
    var currentPlayer: Symbol = Symbol.SYMBOL_CROSS, // X starts
    var turn: Int = 0,
    var result: Result = Result.RESULT_NEW_GAME,

    ) {
    private val time: Long = Date().time

}