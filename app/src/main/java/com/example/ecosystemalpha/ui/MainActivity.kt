package com.example.ecosystemalpha.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.ecosystemalpha.ui.navigation.Navigation
import com.example.ecosystemalpha.ui.theme.EcosystemAlphaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcosystemAlphaTheme {
                Navigation()
            }
        }
    }
}