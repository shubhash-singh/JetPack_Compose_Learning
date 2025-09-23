package com.ragnar.jetpack_compose_learning.screens

import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
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
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ragnar.jetpack_compose_learning.core.TextToSpeech
import com.ragnar.jetpack_compose_learning.ui.theme.BackgroundPrimary
import com.ragnar.jetpack_compose_learning.ui.theme.BackgroundSecondary
import com.ragnar.jetpack_compose_learning.ui.theme.Black
import com.ragnar.jetpack_compose_learning.ui.theme.ColorAccent
import com.ragnar.jetpack_compose_learning.ui.theme.ColorError
import com.ragnar.jetpack_compose_learning.ui.theme.ColorHint
import com.ragnar.jetpack_compose_learning.ui.theme.ColorPrimary
import com.ragnar.jetpack_compose_learning.ui.theme.ColorPrimaryDark
import com.ragnar.jetpack_compose_learning.ui.theme.TextPrimary
import com.ragnar.jetpack_compose_learning.ui.theme.TextSecondary
import com.ragnar.jetpack_compose_learning.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextToSpeechScreen(
    controller: TextToSpeech = viewModel(),
) {
    val context = LocalContext.current
    val state by controller.state.collectAsState()
    var textInput by remember { mutableStateOf("") }



    LaunchedEffect(Unit) {
        controller.initialize(context)
    }

    DisposableEffect(Unit) {
        onDispose {
            controller.cleanup()
        }
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BackgroundPrimary
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
                .background(White),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Character Selection Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = BackgroundSecondary
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Character",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextPrimary
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Boy Character Button
                        OutlinedButton(
                            onClick = { controller.switchCharacter("boy") },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = if (state.selectedCharacter == "boy")
                                    ColorPrimaryDark
                                else White
                            )
                        ) {
                            Icon(Icons.Default.Person,
                                contentDescription = null,
                                tint = if (state.selectedCharacter == "boy") White else Black
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Boy",
                                color = if (state.selectedCharacter == "boy") White else Black
                            )
                        }

                        // Girl Character Button
                        OutlinedButton(
                            onClick = { controller.switchCharacter("girl") },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = if (state.selectedCharacter == "girl")
                                    ColorPrimaryDark
                                else White
                            )
                        ) {
                            Icon(Icons.Default.Person,
                                contentDescription = null,
                                tint = if (state.selectedCharacter == "girl") White else Black
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Girl",
                                color = if (state.selectedCharacter == "girl") White else Black
                            )
                        }
                    }
                }
            }
            // Text Input Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = BackgroundSecondary
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Text Input",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextPrimary
                    )

                    OutlinedTextField(
                        value = textInput,
                        onValueChange = {
                            textInput = it
                            controller.updateText(it)
                        },
                        label = { Text("Enter text to speak") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 120.dp),
                        maxLines = 6,
                        placeholder = { Text("Type your text here...") },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedPlaceholderColor = ColorHint,
                            unfocusedPlaceholderColor = Black,
                            focusedLabelColor = ColorHint,
                            unfocusedTextColor = Black,
                            focusedTextColor = Black
                        )
                    )

                    if (state.detectedLanguage.isNotEmpty()) {
                        Text(
                            text = "Detected: ${state.detectedLanguage}",
                            fontSize = 14.sp,
                            color = ColorError
                        )
                    }
                }
            }
            // Lip Sync Animation
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp),
                colors = CardDefaults.cardColors(
                    containerColor = BackgroundSecondary
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Lip Sync Animation",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            color = TextPrimary
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            IconButton(
                                onClick = { controller.toggleDebug() }
                            ) {
                                Icon(
                                    Icons.Default.BugReport,
                                    contentDescription = "Toggle Debug",
                                    tint = if (state.debugMode) TextPrimary
                                    else TextSecondary
                                )
                            }

                            IconButton(
                                onClick = { controller.testAnimation() }
                            ) {
                                Icon(
                                    Icons.Default.PlayCircleOutline,
                                    contentDescription = "Test Animation",
                                    tint = if(state.isSpeaking) TextPrimary else TextSecondary
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // WebView for Lip Sync
                    AndroidView(
                        factory = {
                            WebView(context).apply {
                            controller.setupWebView(this) // one-time setup
                            }
                                  },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
//                        update = {
//                            // Optional: keep WebView updated with state, instead of reloading
//                            if (state.isSpeaking) {
//                                it.evaluateJavascript("window.AndroidLipSyncAPI.startLipSync('${textInput}')", null)
//                            }
//                        }
                    )
                }
            }

            // Speech Controls Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = BackgroundSecondary
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Speech Controls",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextPrimary
                    )

                    // Speed Control
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Speed", color = TextSecondary)
                            Text(text = "${String.format("%.1f", state.speechRate)}x", color = TextSecondary)
                        }
                        Slider(
                            value = state.speechRate,
                            onValueChange = { controller.setSpeechRate(it) },
                            valueRange = 0.5f..2.0f,
                            steps = 15,
                            colors = SliderDefaults.colors(
                                thumbColor = ColorAccent,
                                activeTrackColor = ColorAccent,
                                inactiveTrackColor = ColorHint,
                                inactiveTickColor = ColorPrimary,
                                activeTickColor = ColorPrimary
                            )
                        )
                    }

                    // Pitch Control
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Pitch", color = TextSecondary)
                            Text("${String.format("%.1f", state.pitch)}x", color = TextSecondary)
                        }
                        Slider(
                            value = state.pitch,
                            onValueChange = { controller.setPitch(it) },
                            valueRange = 0.5f..2.0f,
                            steps = 15,
                            colors = SliderDefaults.colors(
                                thumbColor = ColorAccent,
                                activeTrackColor = ColorAccent,
                                inactiveTrackColor = ColorHint,
                                inactiveTickColor = ColorPrimary,
                                activeTickColor = ColorPrimary
                            )
                        )
                    }

                    // Control Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Button(
                            onClick = { controller.speak(textInput) },
                            enabled = !state.isSpeaking && textInput.isNotBlank() && state.isInitialized,
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = ColorPrimaryDark

                            )
                        ) {
                            Icon(Icons.Default.PlayArrow, contentDescription = null, tint = if (!state.isSpeaking) White else Black)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Speak", color = if (!state.isSpeaking) White else Black)
                        }

                        OutlinedButton(
                            onClick = { controller.stop() },
                            enabled = state.isSpeaking,
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = ColorPrimaryDark

                            )
                        ) {
                            Icon(Icons.Default.Stop, contentDescription = null, tint = if (state.isSpeaking) White else Black)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Stop", color = if (state.isSpeaking) White else Black)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewTextToSpeechScreen(){
    MaterialTheme{
        TextToSpeechScreen()
    }
}