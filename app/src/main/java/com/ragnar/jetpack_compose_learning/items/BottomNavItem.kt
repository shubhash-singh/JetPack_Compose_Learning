package com.ragnar.jetpack_compose_learning.items

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Animation
import androidx.compose.material.icons.outlined.TextFields
import androidx.compose.material.icons.outlined.Speaker


sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {

    object speechToText : BottomNavItem("stt", "Speech To Text", Icons.Outlined.TextFields )
    object textToSpeech : BottomNavItem("tts", "Text To Speech", Icons.Outlined.Speaker)
    object simulationScreen: BottomNavItem("sims", "Simulations", Icons.Outlined.Animation)
}