package it.unipd.dei.esp2021.tictactoe.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.createFontFamilyResolver
import androidx.lifecycle.ViewModel
import it.unipd.dei.esp2021.tictactoe.domain.model.Game
import it.unipd.dei.esp2021.tictactoe.domain.model.Result
import it.unipd.dei.esp2021.tictactoe.domain.model.Symbol
import kotlinx.coroutines.flow.MutableStateFlow

class GameViewModel : ViewModel() {

    var gameState by mutableStateOf(Game())
        private set

    fun setBoardButton(row: Int, col: Int): Int {
        gameState.result = gameState.getResult()
        if (gameState.result != Result.RESULT_NEW_GAME || gameState.result != Result.RESULT_ONGOING) return 1 // If the match is ended
//        val buttonSymbol = gameState.
//        if(buttonSymbol != Symbol.SYMBOL_EMPTY) return 1
//        gameState.setBoardSymbol(row, col)

        return 0
    }
}