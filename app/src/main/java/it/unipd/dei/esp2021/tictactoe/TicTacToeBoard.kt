package it.unipd.dei.esp2021.tictactoe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
//    TicTacToeBoard()
}
@Composable
fun TicTacToeBoard(gameState: TicTacToe) {
    Column {
        Row {
            BoardButton(0,0, gameState)
            BoardButton(0,1, gameState)
            BoardButton(0,2, gameState)
        }
        Row {
            BoardButton(1,0, gameState)
            BoardButton(1,1, gameState)
            BoardButton(1,2, gameState)
        }
        Row {
            BoardButton(2,0, gameState)
            BoardButton(2,1, gameState)
            BoardButton(2,2, gameState)
        }
    }

}

@Composable
fun BoardButton(
    row: Int,
    col: Int,
    gameState: TicTacToe,
    move: (Int,Int) -> Unit = { row: Int, col: Int ->
        try {
            if (gameState.turn % 2 == 0) gameState.setO(row, col)
            else gameState.setX(row, col)
            println(gameState.printFBoard())
            gameState.turn++
        } catch (e: Exception) {
            e.stackTrace
        }
    }
) {
    Button(
        modifier = Modifier
            .size(100.dp, 100.dp)
            .padding(2.dp),
        shape = RectangleShape,
        onClick = { move(row, col) }
    ) {
        //FIX: doesn't update UI
        Text(text = gameState.getSymbolInPosition(row,col))
    }
}