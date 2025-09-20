package com.ragnar.jetpack_compose_learning.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.ragnar.jetpack_compose_learning.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageDropdown(
    onLanguageSelected: (String) -> Unit
) {
    val languages = listOf("English","Hindi", "Tamil", "Telugu", "Kannada")

    var expanded by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf(languages[0]) }

    ExposedDropdownMenuBox(
        modifier = Modifier
            .fillMaxWidth()
            .background(White),
        expanded = expanded,
        onExpandedChange = { expanded = !expanded } // toggle menu open/close
    ) {
        TextField(
            value = selectedLanguage,
            onValueChange = {},
            readOnly = true,
            label = { Text("Select Language") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .background(White)
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },

        ) {
            languages.forEach { language ->
                DropdownMenuItem(
                    text = { Text(language) },
                    onClick = {
                        selectedLanguage = language
                        expanded = false
                        onLanguageSelected(language)
                    },
                )
            }
        }
    }
}
