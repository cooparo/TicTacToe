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

/**
 * ViewModel for managing the state and actions of a Tic Tac Toe game.
 * @property repository The repository for accessing game data.
 */
class GameViewModel(
    private val repository: GameRepository
) : ViewModel() {

    /**
     * Represents the current game state.
     */
    private val _gameState = MutableStateFlow(Game())
    val gameState: StateFlow<Game> = _gameState.asStateFlow()

    /**
     * Represents the current state of the game board.
     */
    private val _board = MutableStateFlow(mutableListOf(mutableListOf(Box())))
    val board: StateFlow<MutableList<MutableList<Box>>> = _board.asStateFlow()

    /**
     * Holds a list of games retrieved from the repository.
     */
    private lateinit var _gamesList: Flow<List<Game>>
    lateinit var gamesList: Flow<List<Game>>

    init {
        this.initGame()
        this.getGamesList()
    }

    /**
     * Initializes the game board to a 3x3 grid of empty boxes and resets the game state.
     */
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

    /**
     * Handles the click event on a specific box in the game board.
     * Updates the board and game state if the box is empty and the game is not ended.
     * @param box The box that was clicked.
     */
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

    /**
     * Handles the computer's move by selecting a random empty box on the board and making a move.
     */
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

    /**
     * Called when the game ends to store the game state in the repository.
     * @param game The game state to store.
     */
    private fun onEndGame(game: Game) = viewModelScope.launch {
        repository.insert(game)
    }

    /**
     * Retrieves the list of past games ordered by date from the repository.
     */
    private fun getGamesList() = viewModelScope.launch {
        _gamesList = repository.allGamesOrderedByDate()
        gamesList = _gamesList
    }

    /**
     * Checks the current board state to determine the game result.
     * @return The result of the game, indicating if there's a winner, a draw, or if the game is ongoing.
     */
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

    /**
     * Checks if a specific symbol has won the game by forming a line horizontally, vertically, or diagonally.
     * @param symbol The symbol to check for a win.
     * @return True if the specified symbol has won, otherwise false.
     * @throws IllegalArgumentException if the symbol is empty.
     */
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

    /**
     * Checks if the board is completely empty.
     * @return True if the board is empty, otherwise false.
     */
    private fun isBoardEmpty(): Boolean {
        for (row in board.value) {
            for (box in row) {
                if (box.symbol != Symbol.SYMBOL_EMPTY) return false
            }
        }
        return true
    }

    /**
     * Checks if the board is completely full.
     * @return True if the board is full, otherwise false.
     */
    private fun isBoardFull(): Boolean {
        for (row in board.value) {
            for (box in row) {
                if (box.symbol == Symbol.SYMBOL_EMPTY) return false
            }
        }
        return true
    }

}