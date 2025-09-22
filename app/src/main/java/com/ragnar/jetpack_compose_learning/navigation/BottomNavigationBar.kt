package com.ragnar.jetpack_compose_learning.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ragnar.jetpack_compose_learning.items.BottomNavItem
import com.ragnar.jetpack_compose_learning.screens.SimulationListScreen
import com.ragnar.jetpack_compose_learning.screens.SpeechToTextScreen
import com.ragnar.jetpack_compose_learning.screens.TextToSpeechScreen


@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.speechToText.route) {

        // load Speech to text screen
        composable(BottomNavItem.speechToText.route) {
            SpeechToTextScreen()
        }
        // load Text To speech screen
        composable(BottomNavItem.textToSpeech.route) {
            TextToSpeechScreen()
        }
        // load all simulations here
        composable(BottomNavItem.simulationScreen.route) {
            SimulationListScreen()
        }

    }
}