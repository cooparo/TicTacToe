package it.unipd.dei.esp2021.tictactoe.view

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun GameScreen() {

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
            BackButton{
                //TODO
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Board()
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            horizontalArrangement = Arrangement.End
        ) {
            RestartGameButton {
                //TODO
            }
        }
    }
}

@Composable
fun BackButton(onClick: () -> Unit) {
    OutlinedButton(onClick = onClick) {
        Text(text = "Back")
    }
}

@Composable
private fun Board() {
    Column {
        for (row in 0..2) {
            Row {
                for (col in 0..2) {
                    BoardButton {
                        //TODO
                    }
                }
            }
        }
    }
}

@Composable
private fun BoardButton(onClick: () -> Unit){
    Button(
        modifier = Modifier
            .size(110.dp)
            .padding(2.dp),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(2.dp, Color.Black),
        onClick = onClick
    ) {
        Text(
            color = Color.Black,
            text = ""
        )
    }
}

@Composable
private fun RestartGameButton(onClick: () -> Unit) {
    OutlinedButton(onClick = onClick) {
        Text(text = "Restart")
    }
}