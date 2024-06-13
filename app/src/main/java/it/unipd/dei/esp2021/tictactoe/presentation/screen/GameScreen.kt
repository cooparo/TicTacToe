package it.unipd.dei.esp2021.tictactoe.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.unipd.dei.esp2021.tictactoe.R
import it.unipd.dei.esp2021.tictactoe.model.Box
import it.unipd.dei.esp2021.tictactoe.model.Game
import it.unipd.dei.esp2021.tictactoe.model.Result
import it.unipd.dei.esp2021.tictactoe.model.Symbol
import it.unipd.dei.esp2021.tictactoe.presentation.GameViewModel
import it.unipd.dei.esp2021.tictactoe.ui.theme.aqua
import it.unipd.dei.esp2021.tictactoe.ui.theme.background
import it.unipd.dei.esp2021.tictactoe.ui.theme.green
import it.unipd.dei.esp2021.tictactoe.ui.theme.yellow
import it.unipd.dei.esp2021.tictactoe.utils.WindowInfo
import it.unipd.dei.esp2021.tictactoe.utils.rememberWindowInfo

/**
 * Composable function that displays the Game screen.
 *
 * The layout of the screen adapts to the available window size, providing
 * different layouts for compact and expanded screens.
 *
 * @param viewModel The [GameViewModel] that provides the game state and handles game logic.
 * @param onNavigateToHome Callback to be invoked when the user wants to navigate back to the home screen.
 */
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
        VerticalLayout(viewModel = viewModel, onNavigateToHome = onNavigateToHome)
        // TODO: big boy (e.g. tablet, ... )
    }
}

/**
 * Composable function that displays the Tic-Tac-Toe board.
 *
 * @param modifier The modifier to be applied to the board layout.
 * @param viewModel The [GameViewModel] that provides the game state and handles game logic.
 */
@Composable
private fun Board(
    modifier: Modifier = Modifier,
    viewModel: GameViewModel
) {
    val board: State<MutableList<MutableList<Box>>> = viewModel.board.collectAsState()
    val currentGame: State<Game> = viewModel.gameState.collectAsState()
    val currentPlayer = currentGame.value.currentPlayer
    val result: Result = currentGame.value.result

    if (currentPlayer == Symbol.SYMBOL_NOUGHT) viewModel.computerMove()
    Column(
        modifier = modifier
    ) {
        board.value.forEach { row ->
            Row {
                row.forEach { box ->
                    BoardButton(result, box) {
                        if (currentPlayer == Symbol.SYMBOL_CROSS) viewModel.onClickBox(box)
                    }
                }
            }
        }
    }
}

/**
 * Composable function that represents a button on the Tic-Tac-Toe board.
 *
 * @param result The current result of the game.
 * @param box The [Box] that this button represents.
 * @param onClick Callback to be invoked when the button is clicked.
 */
@Composable
private fun BoardButton(
    result: Result,
    box: Box,
    onClick: () -> Unit
) {
    var isModified = box.symbol != Symbol.SYMBOL_EMPTY
    isModified =
        if (result.isEnded()) true else isModified // If the game is ended disable all buttons

    val color: Color = if (box.symbol == Symbol.SYMBOL_CROSS) yellow else aqua

    Button(
        modifier = Modifier
            .size(110.dp)
            .padding(2.dp),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(2.dp, Color.White),
        onClick = onClick,
        enabled = !isModified
    ) {
        Text(
            color = color,
            text = box.getSymbol(),
            fontSize = 40.sp
        )
    }
}

/**
 * Composable function that displays the game UI in a vertical layout.
 *
 * This layout is used for devices with a compact screen width.
 *
 * @param viewModel The [GameViewModel] that provides the game state and handles game logic.
 * @param onNavigateToHome Callback to be invoked when the user wants to navigate back to the home screen.
 */
@Composable
private fun VerticalLayout(
    viewModel: GameViewModel,
    onNavigateToHome: () -> Unit
) {
    val currentGame: Game = viewModel.gameState.collectAsState().value
    val gameIsEnded: Boolean = currentGame.result.isEnded()

    var winnerPlayer = ""
    if (currentGame.result.isEnded()) {
        winnerPlayer = when (currentGame.result) {
            Result.RESULT_PLAYER_X -> "You won!"
            Result.RESULT_PLAYER_O -> "You lost"
            Result.RESULT_DRAW -> "That's a draw"
            else -> "You broke the game :)"
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = background,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.15f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (gameIsEnded) {
                    Text(
                        text = winnerPlayer,
                        color = Color.White,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                    )
                } else {
                    HomeTitle()
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Board(viewModel = viewModel)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BackButton(
                    modifier = Modifier.width(150.dp),
                    navigateBack = onNavigateToHome
                )
                RestartGameButton(
                    modifier = Modifier.width(150.dp),
                    onClick = { viewModel.initGame() },
                    isEnabled = gameIsEnded
                )
            }
        }
    }
}

/**
 * Composable function that displays the game UI in a horizontal layout.
 *
 * This layout is used for devices with a compact screen height.
 *
 * @param viewModel The [GameViewModel] that provides the game state and handles game logic.
 * @param onNavigateToHome Callback to be invoked when the user wants to navigate back to the home screen.
 */
@Composable
fun HorizontalLayout(
    viewModel: GameViewModel,
    onNavigateToHome: () -> Unit
) {
    val currentGame: Game = viewModel.gameState.collectAsState().value
    val gameIsEnded: Boolean = currentGame.result.isEnded()

    var winnerPlayer = ""
    if (currentGame.result.isEnded()) {
        winnerPlayer = when (currentGame.result) {
            Result.RESULT_PLAYER_X -> "You won!"
            Result.RESULT_PLAYER_O -> "You lost"
            Result.RESULT_DRAW -> "That's a draw"
            else -> "You broke the game :)"
        }
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = background
    ) {
        Row(
            modifier = Modifier.fillMaxSize(1f)
        ) {
            Board(
                modifier = Modifier
                    .padding(8.dp),
                viewModel = viewModel
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                if (gameIsEnded) {
                    Text(
                        text = winnerPlayer,
                        color = Color.White,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                    )
                } else {
                    HomeTitle()
                }
                Spacer(modifier = Modifier.height(16.dp))
                RestartGameButton(modifier = Modifier.fillMaxWidth(0.8f), isEnabled = gameIsEnded) {
                    viewModel.initGame()
                }
                Spacer(modifier = Modifier.height(8.dp))
                BackButton(modifier = Modifier.fillMaxWidth(0.8f), navigateBack = onNavigateToHome)
            }
        }
    }
}

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = {},
) {
    NavigationButton(
        modifier = modifier,
        onClick = navigateBack,
        text = "Back",
        color = green,
        icon = R.drawable.baseline_arrow_back_24
    )
}

@Composable
fun RestartGameButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean,
    onClick: () -> Unit
) {
    NavigationButton(
        modifier = modifier,
        onClick = onClick,
        text = "Restart",
        isEnabled = isEnabled,
        icon = R.drawable.sharp_directory_sync_24
    )
}