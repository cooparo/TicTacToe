package it.unipd.dei.esp2021.tictactoe.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import it.unipd.dei.esp2021.tictactoe.domain.model.Screen
import it.unipd.dei.esp2021.tictactoe.presentation.screen.GameScreen
import it.unipd.dei.esp2021.tictactoe.presentation.screen.HomeScreen
import it.unipd.dei.esp2021.tictactoe.presentation.screen.StatsScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    viewModel: GameViewModel = GameViewModel(),
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.HomeScreen.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(
                onNavigateToGame = { navController.navigate(Screen.GameScreen.route) },
                onNavigateToStats = { navController.navigate(Screen.StatsScreen.route) }
            )
        }
        composable(route = Screen.GameScreen.route) {
            GameScreen(
                viewModel = viewModel,
                onNavigateToHome = { navController.popBackStack() }
            )
        }
        composable(route = Screen.StatsScreen.route) {
            StatsScreen(
                onNavigateToHome = { navController.popBackStack() }
            )
        }
    }

}
