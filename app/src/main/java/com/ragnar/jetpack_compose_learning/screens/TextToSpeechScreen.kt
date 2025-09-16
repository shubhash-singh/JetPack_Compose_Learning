package com.ragnar.jetpack_compose_learning.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.*


import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@Composable
fun TextToSpeechScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center,

        ) {
        Text(
            text = "Text To Speech Screen",
            color = Color.Black
        )

    }
}