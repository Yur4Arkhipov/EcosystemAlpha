package com.example.ecosystemalpha.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.ecosystemalpha.core.util.UiState
import com.example.ecosystemalpha.ui.navigation.Routes
import com.example.ecosystemalpha.ui.viewmodel.BinScreenViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BinScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: BinScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.binInfo.collectAsState()
    var input by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("BinScreen") },
                actions = {
                    IconButton(
                        onClick = {
                            navHostController.navigate(route = Routes.History)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
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
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Enter the first 6 to 8 digits of a card number (BIN/IIN):",
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                label = { Text("Enter BIN") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(10.dp))

            Button(
                onClick = { viewModel.loadBin(input) },
                enabled = input.length >= 6 && input.length <= 8
            ) {
                Text("Lookup")
            }

            Spacer(Modifier.height(20.dp))

            when(uiState) {
                is UiState.Idle -> { Text("Enter BIN and press the Lookup button") }
                is UiState.Loading -> { CircularProgressIndicator() }
                is UiState.Success -> {
                    val binInfo = (uiState as UiState.Success).data
                    Text("Scheme/Network: ${binInfo.scheme}")
                    Text("Brand: ${binInfo.brand}")
                    Text("Card Number: length:${binInfo.numberLength}, luhn:${binInfo.luhn}")
                    Text("Type: ${binInfo.type}")
                    Text("Prepaid: ${binInfo.prepaid}")
                    Text("Country: ${binInfo.countryEmoji} ${binInfo.countryName}")
                    Text("Latitude: ${binInfo.countryLatitude}, Longitude: ${binInfo.countryLongitude}")
                    Text("Bank name: ${binInfo.bankName}")
                    Text("Bank phone: ${binInfo.bankPhone}")
                    Text("Bank url: ${binInfo.bankUrl}")
                    Text("Bank city: ${binInfo.bankCity}")
                }
                is UiState.Error -> {
                    val error = (uiState as UiState.Error).message
                    Text("Error: $error")
                }
            }
        }
    }
}