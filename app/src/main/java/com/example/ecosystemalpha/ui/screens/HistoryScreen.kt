package com.example.ecosystemalpha.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.ecosystemalpha.core.util.UiState
import com.example.ecosystemalpha.data.local.BinQueryEntity
import com.example.ecosystemalpha.ui.navigation.Routes
import com.example.ecosystemalpha.ui.viewmodel.HistoryScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: HistoryScreenViewModel = hiltViewModel()
) {
    val historyState = viewModel.historyState.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("History") },
                actions = {
                    IconButton(
                        onClick = { navHostController.navigate(route = Routes.Home) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (historyState) {
                UiState.Idle -> {}
                is UiState.Loading -> { CircularProgressIndicator() }
                is UiState.Success<List<BinQueryEntity>> -> {
                    val queries = historyState.data
                    if (queries.isEmpty()) {
                        Text("No history yet")
                    } else {
                        HistoryList(queries)
                    }
                }
                is UiState.Error -> {
                    Text("Error: ${historyState.message}")
                }
            }
        }
    }
}

@Composable
private fun HistoryList(queries: List<BinQueryEntity>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(queries) { query ->
            HistoryCard(query = query)
        }
    }
}

@Composable
private fun HistoryCard(query: BinQueryEntity) {
    Card(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "BIN: ${query.bin}")
            
            Spacer(modifier = Modifier.height(4.dp))

            Text("Scheme/Network: ${query.scheme}")
            Text("Brand: ${query.brand}")
            Text("Card Number: length:${query.numberLength}, luhn:${query.luhn}")
            Text("Type: ${query.type}")
            Text("Prepaid: ${query.prepaid}")
            Text("Country: ${query.countryEmoji} ${query.countryName}")
            Text("Latitude: ${query.countryLatitude}, Longitude: ${query.countryLongitude}")
            Text("Bank name: ${query.bankName}")
            Text("Bank phone: ${query.bankPhone}")
            Text("Bank url: ${query.bankUrl}")
            Text("Bank city: ${query.bankCity}")
        }
    }
}