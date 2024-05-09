package it.unipd.dei.esp2021.tictactoe.presentation.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.unipd.dei.esp2021.tictactoe.model.Game


@Preview(showBackground = true)
@Composable
fun StatsScreen(
    onNavigateToHome: () -> Unit = {},
    gameList: List<Game> = emptyList()
) {

    gameList.forEach { game ->
        Log.d("List", "Game - date: ${game.date}, resuls: ${game.result}, turn: ${game.turn}")
    }

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
        GameList(games = gameList)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
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
    games: List<Game>
) {
    LazyColumn(
        modifier = modifier.height(400.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        userScrollEnabled = true
    ) {
        items(games) { game ->
            GameRow(game = game)
        }
    }
}

@Composable
fun GameRow(game: Game) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "${game.result} ${game.turn} ${game.date}"
            )
        }
    }
}