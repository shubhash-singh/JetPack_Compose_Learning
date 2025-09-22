package com.ragnar.jetpack_compose_learning.items


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


data class SimulationsItem(val name: String, val link: String)

// Composable for a single list item
@Composable
fun UserCard(sims: SimulationsItem) {
    Row(modifier = Modifier.padding(all = 10.dp)) {

        Column {
            Text(text = sims.name, fontWeight = FontWeight.Bold)
            Text(text = "Online", color = Color.Gray)
        }
    }
}