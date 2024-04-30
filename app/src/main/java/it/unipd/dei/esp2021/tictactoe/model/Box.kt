package it.unipd.dei.esp2021.tictactoe.model

data class Box(
    var symbol: Symbol = Symbol.SYMBOL_EMPTY,
    var row: Int = 0,
    var column: Int = 0,
) {

    fun getSymbol(): String = when (symbol) {
        Symbol.SYMBOL_CROSS -> "X"
        Symbol.SYMBOL_NOUGHT -> "O"
        else -> ""
    }
}