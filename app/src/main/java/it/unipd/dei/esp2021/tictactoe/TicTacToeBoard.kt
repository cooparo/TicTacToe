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
    TicTacToeBoard()
}
@Composable
fun TicTacToeBoard() {
    Column {
        Row {
            BoardButton(
                onClick = {

                }
            )
            BoardButton()
            BoardButton()
        }
        Row {
            BoardButton()
            BoardButton()
            BoardButton()
        }
        Row {
            BoardButton()
            BoardButton()
            BoardButton()
        }
    }

}

@Composable
fun BoardButton(onClick: (text: String) -> Unit) {
    Button(
        modifier = Modifier
            .size(100.dp, 100.dp)
            .padding(2.dp),
        shape = RectangleShape,
        onClick = { onClick }
    ) {
        Text("")
    }
}