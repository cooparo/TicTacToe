package it.unipd.dei.esp2021.tictactoe.domain.model

import java.util.Date

class Game(
    var turn: Int = 0,
    var result: Result = Result.RESULT_NEW_GAME,
    val board: Board = Board(),
    val time: Long = Date().time
) {

}