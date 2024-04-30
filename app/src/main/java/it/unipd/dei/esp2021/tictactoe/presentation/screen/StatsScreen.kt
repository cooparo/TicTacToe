package it.unipd.dei.esp2021.tictactoe.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.unipd.dei.esp2021.tictactoe.model.Game
import it.unipd.dei.esp2021.tictactoe.model.Result


@Preview(showBackground = true)
@Composable
fun StatsScreen(
    onNavigateToHome: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Stats",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
        GameList()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = onNavigateToHome) {
                Text(text = "Back")
            }
        }
    }
}

@Composable
fun GameList(
    modifier: Modifier = Modifier,
    games: List<Game> = List(5) { index ->
        Game(result = Result.RESULT_PLAYER_X, id = index)
    }
) {
    LazyColumn(
        modifier = modifier,

        ) {
        items(games) { game ->
            GameRow(game = game)
        }
    }
}

@Composable
fun GameRow(game: Game) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = game.id.toString())
        Text(text = game.result.toString())
        Text(text = game.date.toString())
    }
}