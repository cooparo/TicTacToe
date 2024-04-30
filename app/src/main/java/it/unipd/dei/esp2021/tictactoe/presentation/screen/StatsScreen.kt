package it.unipd.dei.esp2021.tictactoe.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import it.unipd.dei.esp2021.tictactoe.domain.model.Screen

@Preview(showBackground = true)
@Composable
fun StatsScreen(
    onNavigateToHome: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Hello my dear")
        Button(onClick = onNavigateToHome) {
            Text(text = "Back")
        }
    }
}