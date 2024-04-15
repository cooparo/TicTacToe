package it.unipd.dei.esp2021.tictactoe.domain.model

import it.unipd.dei.esp2021.tictactoe.domain.model.Symbol.SYMBOL_EMPTY
class Board(
    //matrix[row][col]
    private val matrix: Array<Array<Symbol>> = arrayOf(
        arrayOf(SYMBOL_EMPTY, SYMBOL_EMPTY, SYMBOL_EMPTY),
        arrayOf(SYMBOL_EMPTY, SYMBOL_EMPTY, SYMBOL_EMPTY),
        arrayOf(SYMBOL_EMPTY, SYMBOL_EMPTY, SYMBOL_EMPTY),
    )
) {


}