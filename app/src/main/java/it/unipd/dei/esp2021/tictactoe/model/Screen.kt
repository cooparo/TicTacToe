package it.unipd.dei.esp2021.tictactoe.model

sealed class Screen(val route: String) {
    data object HomeScreen : Screen(route = "home_screen")
    data object GameScreen : Screen(route = "game_screen")
    data object StatsScreen : Screen(route = "stats_screen")
}