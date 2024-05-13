package it.unipd.dei.esp2021.tictactoe.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.unipd.dei.esp2021.tictactoe.data.GameRepository
import it.unipd.dei.esp2021.tictactoe.model.Box
import it.unipd.dei.esp2021.tictactoe.model.Game
import it.unipd.dei.esp2021.tictactoe.model.Result
import it.unipd.dei.esp2021.tictactoe.model.Symbol
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameViewModel(
    private val repository: GameRepository
) : ViewModel() {

    private val _gameState = MutableStateFlow(Game())
    val gameState: StateFlow<Game> = _gameState.asStateFlow()

    private val _board = MutableStateFlow(mutableListOf(mutableListOf(Box())))
    val board: StateFlow<MutableList<MutableList<Box>>> = _board.asStateFlow()

    private lateinit var _gamesList: Flow<List<Game>>
    lateinit var gamesList: Flow<List<Game>>

    init {
        this.initGame()
        this.getGamesList()
    }

    fun initGame() {
        var row = 0
        var column: Int

        _board.value = MutableList(3) {
            column = 0

            MutableList(3) {
                Box(
                    row = row++ / 3,
                    column = column++
                )
            }
        }

        _gameState.value = Game()
    }

    fun onClickBox(box: Box) = viewModelScope.launch {
        val currentPlayer: Symbol = _gameState.value.currentPlayer
        val turn: Int = _gameState.value.turn
        val isEmpty: Boolean = box.symbol == Symbol.SYMBOL_EMPTY

        if (isEmpty && !_gameState.value.result.isEnded()) {

            board.value[box.row][box.column].symbol = currentPlayer

            _gameState.update { currentGame ->
                currentGame.copy(
                    currentPlayer = currentPlayer.next(),
                    turn = turn + 1,
                    result = checkResult()
                )
            }
            if (_gameState.value.result.isEnded()) onEndGame(_gameState.value)
        }
    }

    fun computerMove() = viewModelScope.launch {
        if (!_gameState.value.result.isEnded()) {
            var randomEmptyBox = Box()
            var isFound = false

            while (!isFound) {
                val randRow: Int = Random.nextInt(0, 3)
                val randCol: Int = Random.nextInt(0, 3)

                val box = board.value[randRow][randCol]
                if (box.symbol == Symbol.SYMBOL_EMPTY) {
                    randomEmptyBox = box
                    isFound = true
                }
            }

            onClickBox(randomEmptyBox)
        }
    }

    private fun onEndGame(game: Game) = viewModelScope.launch {
        repository.insert(game)
    }

    private fun getGamesList() = viewModelScope.launch {
        _gamesList = repository.allGamesOrderedByDate()
        gamesList = _gamesList
    }

    @Suppress("LocalVariableName")
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
        for (i in 0..2) {
            val row = board[i]
            if (row[0].symbol == symbol && row[1].symbol == symbol && row[2].symbol == symbol) return true
        }

        // Check all columns
        for (i in 0..2) {
            if (board[0][i].symbol == symbol && board[1][i].symbol == symbol && board[2][i].symbol == symbol) return true
        }

        // Check diagonals
        return (board[0][0].symbol == symbol && board[1][1].symbol == symbol && board[2][2].symbol == symbol) ||
                (board[0][2].symbol == symbol && board[1][1].symbol == symbol && board[2][0].symbol == symbol)
    }

    private fun isBoardEmpty(): Boolean {
        for (row in board.value) {
            for (box in row) {
                if (box.symbol != Symbol.SYMBOL_EMPTY) return false
            }
        }
        return true
    }

    private fun isBoardFull(): Boolean {
        for (row in board.value) {
            for (box in row) {
                if (box.symbol == Symbol.SYMBOL_EMPTY) return false
            }
        }
        return true
    }

}