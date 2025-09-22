package com.ragnar.jetpack_compose_learning.screens

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ragnar.jetpack_compose_learning.core.SpeechToText
import com.ragnar.jetpack_compose_learning.menu.LanguageDropdown
import com.ragnar.jetpack_compose_learning.ui.theme.BackgroundPrimary
import com.ragnar.jetpack_compose_learning.ui.theme.BackgroundSecondary
import com.ragnar.jetpack_compose_learning.ui.theme.Black
import com.ragnar.jetpack_compose_learning.ui.theme.ColorError
import com.ragnar.jetpack_compose_learning.ui.theme.ColorSuccess
import com.ragnar.jetpack_compose_learning.ui.theme.White


@Composable
fun SpeechToTextScreen(
    controller: SpeechToText = viewModel(),
) {
    val context = LocalContext.current
    var outputText by remember { mutableStateOf("") }
    var selectedLanguage by remember { mutableStateOf("English") }

    val state by controller.state.collectAsState()

    // Permission launcher
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        controller.handlePermissionResult(
            SpeechToText.RECORD_AUDIO_PERMISSION_REQUEST,
            if (isGranted) intArrayOf(android.content.pm.PackageManager.PERMISSION_GRANTED)
            else intArrayOf(android.content.pm.PackageManager.PERMISSION_DENIED)
        )
    }

    LaunchedEffect(Unit) {
        controller.initialize(context)
        // Request permission if not granted
        if (!state.hasPermission) {
            permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            controller.destroy()
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BackgroundPrimary
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
                .background(White),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = BackgroundSecondary
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(0.dp)
                ) {
                    LanguageDropdown (
                        onLanguageSelected = { language ->
                            val languageCode = when(language) {
                                "English" -> "en-IN"
                                "हिंदी (Hindi)" -> "hi-IN"
                                "ಕನ್ನಡ (Kannada)" -> "kn-IN"
                                "தமிழ் (Tamil)" -> "ta-IN"
                                "తెలుగు (Telugu)" -> "te-IN"
                                else -> "en-IN"
                            }
                            controller.setLanguage(languageCode)
                            selectedLanguage = language
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // "Selected: English" Text
                    Text(
                        text = "Selected: $selectedLanguage",
                        color = ColorError,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(Alignment.Start)
                    )
                }
            }

            // Microphone Icon
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = BackgroundSecondary
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(40.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Status: ",
                            color = if (state.statusMessage.contains("error", ignoreCase = true))
                                ColorError else ColorSuccess,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.padding(2.dp))
                        Text(
                            text = state.statusMessage,
                            color = if (state.statusMessage.contains("error", ignoreCase = true))
                                ColorError else ColorSuccess,
                            style = MaterialTheme.typography.titleMedium

                        )
                    }
                    OutlinedButton (
                        onClick = {
                            if (!state.isSpeaking) {
                                if (state.hasPermission and state.isInitialized) {
                                    controller.startListening()
                                }
                                else if (!state.hasPermission) {
                                    permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                                }
                            }else {
                                controller.stopListening()
                            }
                        },
                        shape = CircleShape,
                        modifier = Modifier.size(80.dp),
                        border = if (!state.isSpeaking)
                            BorderStroke(2.dp, ColorSuccess) else BorderStroke(2.dp, ColorError),
                        enabled = state.hasPermission and state.isInitialized
                    ) {
                        Icon(
                            imageVector = if (!state.isSpeaking)
                                Icons.Filled.Mic else Icons.Default.Stop,
                            contentDescription = "Start Speech Recognition",
                            modifier = Modifier.size(50.dp),
                            tint = if (!state.isSpeaking)
                                ColorSuccess else ColorError

                        )
                    }
                    Text(
                        text = if (state.isSpeaking) "Tap to stop" else "Tap to start",
                        style = MaterialTheme.typography.bodySmall,
                        color = Black
                    )
                }
            }


            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = BackgroundSecondary
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //  "Recognized Text:" Label
                    Text(
                        text = "Recognized Text:",
                        color = Black,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.Start)
                    )

                    // 6. Recognized Text Box
                    OutlinedTextField(
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Black,
                            unfocusedTextColor = Black,
                            errorTextColor = ColorError,
                            focusedContainerColor = White,
                            unfocusedContainerColor = White,
                            focusedBorderColor = Black,
                            unfocusedBorderColor = Black
                        ),
                        value = state.resultText.ifEmpty { "Your speech will appear here..." },
                        onValueChange = { /* Read Only */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        readOnly = true
                    )
                }
            }
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