package it.unipd.dei.esp2021.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import it.unipd.dei.esp2021.tictactoe.data.GameDatabase
import it.unipd.dei.esp2021.tictactoe.data.GameRepository
import it.unipd.dei.esp2021.tictactoe.presentation.AppNavHost
import it.unipd.dei.esp2021.tictactoe.presentation.GameViewModel
import it.unipd.dei.esp2021.tictactoe.presentation.GameViewModelFactory

class MainActivity : ComponentActivity() {

    private val gameViewModel: GameViewModel by viewModels {
        GameViewModelFactory(GameRepository.getInstance(GameDatabase.getDatabase(this).dao))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavHost(viewModel = gameViewModel)
        }
    }
}





