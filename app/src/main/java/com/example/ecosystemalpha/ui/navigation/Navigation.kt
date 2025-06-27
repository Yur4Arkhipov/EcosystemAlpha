package com.example.ecosystemalpha.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecosystemalpha.ui.screens.BinScreen
import com.example.ecosystemalpha.ui.screens.HistoryScreen

@Composable
fun Navigation() {
    val navHostController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(
            navController = navHostController,
            startDestination = Routes.Home,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<Routes.Home> { BinScreen(navHostController) }
            composable<Routes.History> { HistoryScreen(navHostController) }
        }
    }
}