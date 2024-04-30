package it.unipd.dei.esp2021.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import it.unipd.dei.esp2021.tictactoe.presentation.AppNavHost
import it.unipd.dei.esp2021.tictactoe.presentation.GameViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavHost(viewModel = viewModel)
        }
    }
}





