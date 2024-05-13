package it.unipd.dei.esp2021.tictactoe.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.unipd.dei.esp2021.tictactoe.model.Box
import it.unipd.dei.esp2021.tictactoe.model.Game
import it.unipd.dei.esp2021.tictactoe.model.Result
import it.unipd.dei.esp2021.tictactoe.model.Symbol
import it.unipd.dei.esp2021.tictactoe.presentation.GameViewModel
import it.unipd.dei.esp2021.tictactoe.utils.WindowInfo
import it.unipd.dei.esp2021.tictactoe.utils.rememberWindowInfo

@Composable
fun GameScreen(
    viewModel: GameViewModel,
    onNavigateToHome: () -> Unit = {}
) {
    val windowInfo = rememberWindowInfo()

    if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
        VerticalLayout(viewModel = viewModel, onNavigateToHome = onNavigateToHome)
    } else if (windowInfo.screenHeightInfo is WindowInfo.WindowType.Compact) {
        HorizontalLayout(viewModel = viewModel, onNavigateToHome = onNavigateToHome)
    } else {
        // TODO: big boy (e.g. tablet, ... )
    }

}

@Composable
fun BackButton(onClick: () -> Unit) {
    OutlinedButton(onClick = onClick) {
        Text(text = "Back")
    }
}

@Composable
private fun Board(viewModel: GameViewModel) {
    val board: State<MutableList<MutableList<Box>>> = viewModel.board.collectAsState()
    val currentGame: State<Game> = viewModel.gameState.collectAsState()
    val currentPlayer = currentGame.value.currentPlayer
    val result: Result = currentGame.value.result

    if (currentPlayer == Symbol.SYMBOL_NOUGHT) viewModel.computerMove()
    Column {
        board.value.forEach { row ->
            Row {
                row.forEach { box ->
                    BoardButton(result, box) {
                        if (currentPlayer == Symbol.SYMBOL_CROSS) viewModel.onClickBox(box)
                    }
                }
            }
        }
        if (result.isEnded()) {
            val winnerPlayer = when (result) {
                Result.RESULT_PLAYER_X -> "Player X"
                Result.RESULT_PLAYER_O -> "Player O"
                Result.RESULT_DRAW -> "Draw, nobody"
                else -> "You broke the game :)"
            }

            Text(text = "$winnerPlayer won!")
        }
    }
}

@Composable
private fun BoardButton(result: Result, box: Box, onClick: () -> Unit) {
    var isModified = box.symbol != Symbol.SYMBOL_EMPTY
    isModified =
        if (result.isEnded()) true else isModified // If the game is ended disable all buttons

    Button(
        modifier = Modifier
            .size(110.dp)
            .padding(2.dp),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(2.dp, Color.Black),
        onClick = onClick,
        enabled = !isModified
    ) {
        Text(
            color = Color.Black,
            text = box.getSymbol(),
            fontSize = 40.sp
        )
    }
}


@Composable
private fun RestartGameButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = "Restart")
    }
}

@Composable
private fun VerticalLayout(
    viewModel: GameViewModel,
    onNavigateToHome: () -> Unit
) {
    val currentGame: Game = viewModel.gameState.collectAsState().value
    val gameIsEnded: Boolean = currentGame.result.isEnded()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            BackButton(onClick = onNavigateToHome)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Board(viewModel)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            horizontalArrangement = Arrangement.End
        ) {
            if (gameIsEnded) {
                RestartGameButton {
                    viewModel.initGame()
                }
            }
        }
    }
}

@Composable
fun HorizontalLayout(
    viewModel: GameViewModel,
    onNavigateToHome: () -> Unit
) {
    val currentGame: Game = viewModel.gameState.collectAsState().value
    val gameIsEnded: Boolean = currentGame.result.isEnded()


}