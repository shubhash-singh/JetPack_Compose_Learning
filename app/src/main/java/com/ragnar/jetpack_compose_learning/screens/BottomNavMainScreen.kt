package com.ragnar.jetpack_compose_learning.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ragnar.jetpack_compose_learning.items.BottomNavItem
import com.ragnar.jetpack_compose_learning.navigation.BottomNavGraph
import com.ragnar.jetpack_compose_learning.ui.theme.BackgroundSecondary
import com.ragnar.jetpack_compose_learning.ui.theme.TextPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavMainScreen() {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    val items = listOf(
        BottomNavItem.textToSpeech,
        BottomNavItem.speechToText
    )
    val topBarTitle: String = when (currentRoute) {
        BottomNavItem.textToSpeech.route -> "Text to Speech"
        BottomNavItem.speechToText.route -> "Speech to Text"
        else -> "NCERT Learning App" // default title
    }

    Scaffold(
        // Top App Bar
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text (
                        text = topBarTitle,
                        color = TextPrimary,
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = BackgroundSecondary,
                    titleContentColor = TextPrimary
                ),
                modifier = Modifier.background(BackgroundSecondary),


            )
        },
        // Bottom Navigation Bar
        bottomBar = {
            NavigationBar(
                containerColor = BackgroundSecondary,
                tonalElevation = 8.dp // subtle shadow
//                modifier = Modifier
//                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)) // rounded corners
            ) {


                items.forEach { item ->

                    val selected = currentRoute == item.route

                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true

                            }
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label,
                                tint = if (selected)
                                    Color.Black
                                else
                                    Color.Gray
                            )
                        },
                        label = {
                            if (selected) {
                                Text(
                                    text = item.label,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.Black
                                )
                            }
                        },
                        alwaysShowLabel = false,
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent // removes grey background
                        )
                    )
                }
            }
        }
    ){ innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            BottomNavGraph(navController)
        }


    }
}