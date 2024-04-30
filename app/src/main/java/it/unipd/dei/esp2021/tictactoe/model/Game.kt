package it.unipd.dei.esp2021.tictactoe.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "games")
data class Game(
    var turn: Int = 0,
    var result: Result = Result.RESULT_NEW_GAME,
    var date: Long = Date().time,
    @Ignore var currentPlayer: Symbol = Symbol.SYMBOL_CROSS, // X starts
    @PrimaryKey(autoGenerate = true) var id: Int = -1
)