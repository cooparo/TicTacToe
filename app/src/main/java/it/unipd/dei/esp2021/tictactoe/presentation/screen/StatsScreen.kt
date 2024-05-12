package it.unipd.dei.esp2021.tictactoe.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.unipd.dei.esp2021.tictactoe.model.Game
import it.unipd.dei.esp2021.tictactoe.model.Result
import it.unipd.dei.esp2021.tictactoe.ui.theme.alt_background
import it.unipd.dei.esp2021.tictactoe.ui.theme.background
import it.unipd.dei.esp2021.tictactoe.ui.theme.blue
import it.unipd.dei.esp2021.tictactoe.ui.theme.green
import it.unipd.dei.esp2021.tictactoe.ui.theme.red
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

val listGame = listOf(
    Game(8, Result.RESULT_PLAYER_X, Date().time),
    Game(10, Result.RESULT_DRAW, Date().time),
    Game(6, Result.RESULT_PLAYER_O, Date().time),
    Game(8, Result.RESULT_PLAYER_X, Date().time),
    Game(10, Result.RESULT_DRAW, Date().time),
    Game(6, Result.RESULT_PLAYER_O, Date().time),
    Game(8, Result.RESULT_PLAYER_X, Date().time),
)

@Preview(showBackground = true)
@Composable
fun StatsScreen(
    onNavigateToHome: () -> Unit = {},
    gameList: List<Game> = listGame
) {

   Surface(
       modifier = Modifier.fillMaxSize(),
       color = background
   ) {
       Column(
           modifier = Modifier.fillMaxSize(),
           verticalArrangement = Arrangement.SpaceBetween

           ) {
           Row(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(16.dp),
               horizontalArrangement = Arrangement.Center
           ) {
               Text(
                   text = "STATS",
                   fontSize = 30.sp,
                   fontWeight = FontWeight.Bold,
                   color = Color.White
               )
           }
           GameList(
               modifier = Modifier
                   .fillMaxWidth()
                   .fillMaxHeight(0.8f),
               games = gameList
           )
           Row(
               modifier = Modifier
                   .fillMaxWidth()
                   .fillMaxHeight(0.4f)
                   .padding(8.dp),
               horizontalArrangement = Arrangement.SpaceBetween
           ) {
               ElevatedButton(
                   onClick = onNavigateToHome

               ) {
                   Text(text = "Back")
               }
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
        modifier = modifier,
        userScrollEnabled = true
    ) {
        items(games) { game ->
            GameRow(game = game)
        }
    }
}

@Composable
fun GameRow(
    game: Game = Game(8, Result.RESULT_PLAYER_X, Date().time)
) {
    var resultColor = Color.White
    val fResult = when(game.result) {
        Result.RESULT_NEW_GAME -> ""
        Result.RESULT_ONGOING -> ""
        Result.RESULT_PLAYER_X -> {
            resultColor = green
            "WON"
        }
        Result.RESULT_PLAYER_O ->{
            resultColor = red
            "LOST"
        }
        Result.RESULT_DRAW -> "DRAW"
    }

    val fDate = SimpleDateFormat("HH:mm - dd/MM/yyyy", Locale.ITALY).format(Date(game.date))

    ElevatedCard (
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        colors = CardDefaults.cardColors(alt_background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = fResult,
                fontWeight = FontWeight.Bold,
                color = resultColor
            )
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = game.turn.toString(),
                color = Color.White
            )
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = fDate,
                color = Color.White
            )
        }
    }
}