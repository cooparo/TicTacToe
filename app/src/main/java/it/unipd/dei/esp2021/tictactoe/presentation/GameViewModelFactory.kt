package it.unipd.dei.esp2021.tictactoe.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import it.unipd.dei.esp2021.tictactoe.data.GameRepository

class GameViewModelFactory(
    private val gameRepository: GameRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameViewModel(gameRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
