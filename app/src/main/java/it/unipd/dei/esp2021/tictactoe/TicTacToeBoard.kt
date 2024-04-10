package it.unipd.dei.esp2021.tictactoe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TicTacToeBoard()
}
@Composable
fun TicTacToeBoard() {
    val gameState = TicTacToe()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
    gameState: TicTacToe
) {
    var text by remember { mutableStateOf("") }
    Button(
        modifier = Modifier
            .size(100.dp, 100.dp)
            .padding(2.dp),
        shape = RectangleShape,
        onClick = {
            try {
                // Checks which player's turn is
                if (gameState.turn % 2 == 0) gameState.setO(row, col)
                else gameState.setX(row, col)
                println(gameState.printFBoard()) // debug purpose
                text = gameState.getSymbolInPosition(row, col)

                gameState.checkResult()
                println("Result: ${gameState.result}")
                gameState.turn++
            } catch (e: Exception) {
                e.stackTrace
            }
        },
    ) {
        Text(text)
    }
}