package it.unipd.dei.esp2021.tictactoe.model

enum class Symbol {
    SYMBOL_EMPTY,
    SYMBOL_CROSS,
    SYMBOL_NOUGHT;

    fun next(): Symbol = if (this == SYMBOL_CROSS) SYMBOL_NOUGHT else SYMBOL_CROSS
}