package it.unipd.dei.esp2021.tictactoe.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.unipd.dei.esp2021.tictactoe.R
import it.unipd.dei.esp2021.tictactoe.ui.theme.background
import it.unipd.dei.esp2021.tictactoe.ui.theme.blue
import it.unipd.dei.esp2021.tictactoe.ui.theme.green

/**
 * Composable function that displays the Home screen.
 *
 * @param onNavigateToGame Callback to be invoked when the user wants to navigate to the game screen.
 * @param onNavigateToStats Callback to be invoked when the user wants to navigate to the statistics screen.
 */
@Preview(showBackground = true)
@Composable
fun HomeScreen(
    onNavigateToGame: () -> Unit = {},
    onNavigateToStats: () -> Unit = {}
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            HomeTitle()
            Column(
                modifier = Modifier
                    .padding(horizontal = 32.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NavigationButton(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    onClick = onNavigateToGame,
                    text = "PLAY",
                    color = blue,
                    icon = R.drawable.baseline_play_arrow_24
                )
                Spacer(modifier = Modifier.height(16.dp))
                NavigationButton(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    onClick = onNavigateToStats,
                    text = "STATS",
                    color = green,
                    icon = R.drawable.baseline_bar_chart_24
                )
            }
        }
    }
}

@Composable
fun NavigationButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    text: String = "",
    color: Color = blue,
    icon: Int = R.drawable.ic_launcher_foreground,
    isEnabled: Boolean = true
) {
    val enabledColor = if (isEnabled) Color.White else Color.Gray
    ElevatedButton(
        onClick = onClick,
        modifier = modifier
            .height(60.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        enabled = isEnabled,
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = text,
            tint = enabledColor,
            modifier = Modifier.size(24.dp),
        )
        Text(
            text = text,
            fontSize = 18.sp,
            color = enabledColor,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun HomeTitle() {
    Text(
        text = "TicTacToe",
        fontSize = 60.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Cursive,
    )
}
