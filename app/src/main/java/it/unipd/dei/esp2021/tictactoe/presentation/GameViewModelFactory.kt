package it.unipd.dei.esp2021.tictactoe.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import it.unipd.dei.esp2021.tictactoe.data.GameRepository

/**
 * Factory class for creating instances of [GameViewModel].
 * This factory is used to inject the [GameRepository] into the [GameViewModel].
 * @property gameRepository The repository instance used for accessing game data.
 */
class GameViewModelFactory(
    private val gameRepository: GameRepository
) : ViewModelProvider.NewInstanceFactory() {

    /**
     * Creates a new instance of the specified [ViewModel] class.
     * @param T The type of the [ViewModel] to be created.
     * @param modelClass The class of the [ViewModel] to be created.
     * @return A new instance of [GameViewModel] if the specified modelClass is assignable from [GameViewModel].
     * @throws IllegalArgumentException If the modelClass is not assignable from [GameViewModel].
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameViewModel(repository = gameRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
