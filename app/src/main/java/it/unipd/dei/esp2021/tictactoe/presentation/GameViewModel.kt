package it.unipd.dei.esp2021.tictactoe.presentation

import androidx.lifecycle.ViewModel
import it.unipd.dei.esp2021.tictactoe.domain.model.Game
import kotlinx.coroutines.flow.MutableStateFlow

class GameViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(Game())

    fun onClickBoardButton() {

    }
}