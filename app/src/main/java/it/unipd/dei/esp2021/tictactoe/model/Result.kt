package it.unipd.dei.esp2021.tictactoe.model

enum class Result {
    RESULT_NEW_GAME,
    RESULT_ONGOING,
    RESULT_PLAYER_X,
    RESULT_PLAYER_O,
    RESULT_DRAW;

    fun isEnded() = this == RESULT_DRAW || this == RESULT_PLAYER_O || this == RESULT_PLAYER_X
}