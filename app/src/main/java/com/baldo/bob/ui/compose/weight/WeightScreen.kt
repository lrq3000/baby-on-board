package com.baldo.bob.ui.compose.weight

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.baldo.bob.ui.AppViewModelProvider
import com.baldo.bob.ui.compose.weight.viewModel.WeightViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WeightScreen(
    viewModel: WeightViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val openDialog = remember { mutableStateOf(false) }
    val weightUiState by viewModel.weightUiState.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { openDialog.value = true }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add note")
            }
        }) {
        if (openDialog.value) {
            AddWeightScreen(openDialog = openDialog)
        }
        WeightBody()
    }
}

@Composable
fun WeightBody() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "test")
    }
}