package com.ragnar.jetpack_compose_learning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ragnar.jetpack_compose_learning.screens.BottomNavMainScreen
import com.ragnar.jetpack_compose_learning.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                BottomNavMainScreen()
            }
        }
    }
}