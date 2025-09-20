package com.ragnar.jetpack_compose_learning.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ragnar.jetpack_compose_learning.menu.LanguageDropdown
import com.ragnar.jetpack_compose_learning.ui.theme.BackgroundPrimary
import com.ragnar.jetpack_compose_learning.ui.theme.Black
import com.ragnar.jetpack_compose_learning.ui.theme.ColorError
import com.ragnar.jetpack_compose_learning.ui.theme.ColorHint

@Composable
fun SpeechToTextScreen() {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BackgroundPrimary
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Language Input Field
            LanguageDropdown { selected ->

            }

            Spacer(modifier = Modifier.height(8.dp))

            // "Selected: English" Text
            Text(
                text = "Selected: English",
                color = ColorError,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Microphone Icon
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(color = Color(0xFFE0D6FF), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Mic,
                    contentDescription = "Start Speech Recognition",
                    modifier = Modifier.size(50.dp),
                    tint = ColorHint
                )
            }

            // This Spacer pushes the content below it towards the bottom
            Spacer(modifier = Modifier.weight(1f))

            // "Recognized Text:" Label
            Text(
                text = "Recognized Text:",
                color = Black,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 6. Recognized Text Box
            OutlinedTextField(
//                colors = (),
                value = "Your speech will appear here...",
                onValueChange = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                readOnly = true
            )
        }
    }
}

// Preview function to display the layout in Android Studio
@Preview(showBackground = true)
@Composable
fun SpeechToTextScreenPreview() {

    MaterialTheme {
        SpeechToTextScreen()
    }
}