package com.baldo.bob.ui.compose.weight

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.baldo.bob.data.Weight
import com.baldo.bob.ui.AppViewModelProvider
import com.baldo.bob.ui.compose.weight.viewModel.WeightViewModel
import java.text.DateFormat

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WeightScreen(
    onAddButtonClicked: () -> Unit,
    viewModel: WeightViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val weightUiState by viewModel.weightUiState.collectAsState()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onAddButtonClicked() }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add note")
            }
        }) {
        WeightBody(weightList = weightUiState.weightList)
    }
}

@Composable
fun WeightBody(weightList: List<Weight>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        WeightList(weightList = weightList)
    }
}

@Composable
fun WeightList(weightList: List<Weight>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(items = weightList, key = { it.date }) {
            DisplayWeigh(weight = it)
        }
    }
}

@Composable
fun DisplayWeigh(weight: Weight) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(8.dp)
            )

    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceAround) {
            Text(text = DateFormat.getDateInstance(DateFormat.LONG).format(weight.date))
            Text(text = weight.weight.toString() + " kg")
        }
    }
}