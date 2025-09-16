package com.ragnar.jetpack_compose_learning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ragnar.jetpack_compose_learning.ui.theme.AppTheme
import com.ragnar.jetpack_compose_learning.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                UpdateTextField()
            }
        }
    }
    @Composable
    fun UpdateTextField() {

//        state of input text
        var input by remember { mutableStateOf("") }
//        state of output text
        var displayText by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = displayText
            )
//            adds space between elements
            Spacer(modifier = Modifier.padding(10.dp))

            OutlinedTextField(
                value = input,
                onValueChange = { inputString: String ->
                                input = inputString },
                label = {
                    Text(
                        text = "Enter your name",
                        color = MyTeal
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = MyTeal,
                    unfocusedTextColor = Color.Gray,
                    cursorColor = MyTeal,
                    focusedLabelColor = MyTeal,
                    unfocusedLabelColor = Color.Gray,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Button( onClick = {
                displayText = input
            }) {
                Text("Show")
            }
        }
    }
}