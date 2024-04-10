package it.unipd.dei.esp2021.tictactoe

/**
 *  Every instance of TicTacToe class is a game.
 *  If you live under a rock and you don't know how the game works,
 *  for game's rules check https://en.wikipedia.org/wiki/Tic-tac-toe.
 *
 *  @param board A matrix where you play the game; the value in the matrix are defined like this:
 *  - 0 stands for blank cell (-),
 *  - 1 for O (Nought),
 *  - 2 for X (Cross).
 */
class TicTacToe(
    // board[row][column]
    private val board: Array<Array<Int>> = arrayOf(
        arrayOf(0,0,0),
        arrayOf(0,0,0),
        arrayOf(0,0,0)
    ),
    var turn: Int = 0,
    var result: Int = 0
) {
    fun setO(row: Int, col: Int) {
        if (board[row][col] != 0 || XhasWon() || OhasWon()) throw Exception()
        board[row][col] = 1
    }
    fun setX(row: Int, col: Int) {
        if (board[row][col] != 0 || XhasWon() || OhasWon()) throw Exception()
        board[row][col] = 2
    }

    private fun getValueInPosition(row: Int, col: Int): Int {
        return board[row][col]
    }

    fun getSymbolInPosition(row: Int, col: Int): String {
        return when (getValueInPosition(row, col)) {
            1 -> "O"
            2 -> "X"
            else -> ""
        }
    }
    /**
     *  Print to Logcat the formatted board. Just for debugging purpose.
     *  @return String, print the board of the game, like this
     *  X - X
     *  - O X
     *  - - O
     */
    fun printFBoard() {
        for(row in this.board) {
            for ( element in row ) {
                when(element) {
                    0 -> print("- ")
                    1 -> print("O ")
                    2 -> print("X ")
                }
            }
            println()
        }
    }

    /**
     *  Print to Logcat the board. Just for debugging purpose.
     *  @return print the board of the game, like this \n
     *  2 0 2 \n
     *  0 1 2 \n
     *  0 0 1
     */
    fun printBoard() {
        for(row in this.board) {
            for ( element in row ) print("$element ")
            println()
        }
    }

    /**
     * Check if the game is ended and if true returns the result.
     * @return Integer:
     * - 0, for an ongoing game
     * - 1, if O (Nought) wins
     * - 2, if X (Crosses) wins
     * - 3, for a draw
     */
    fun checkResult() {
        if (isBoardFull() && !XhasWon() && !OhasWon()) result = 3 //draw
        if (OhasWon()) result = 1
        if (XhasWon()) result = 2
    }

    /**
     * Checks if the board is full (there are no 0/blank space in the board).
     * @return true it is, false otherwise
     */
    private fun isBoardFull(): Boolean {
        for(row in this.board) {
            for ( element in row ) if (element==0) return false
        }
        return true
    }

    private fun OhasWon(): Boolean {
        return hasWon(1)
    }
    private fun XhasWon(): Boolean {
        return hasWon(2)
    }

    /**
     * Check if player has won.
     * @param player can be equal only to 1 or 2
     * @return true if the given player has won, false otherwise
     */
    private fun hasWon(player: Int): Boolean {

        // Check all rows
        for (row in this.board) {
            if(row[0]==player && row[1]==player && row[2]==player) return true
        }

        // Check all columns
        for (i in 0..2) {
            if ( board[0][i]==player && board[1][i]==player && board[2][i]==player) return true
        }

        // Check diagonals
        if (
            (board[0][0]==player && board[1][1]==player && board[2][2]==player) ||
            ( board[0][2]==player && board[1][1]==player && board[2][0]==player)
        ) return true

        return false
    }
}
