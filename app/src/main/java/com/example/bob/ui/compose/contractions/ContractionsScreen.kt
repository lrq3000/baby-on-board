package com.example.bob.ui.compose.contractions

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.alpha
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bob.data.Contraction
import com.example.bob.ui.AppViewModelProvider
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.Date
import java.util.Locale

fun formatedHour(date: Date): String {
    return DateFormat.getTimeInstance(DateFormat.SHORT, Locale.FRANCE).format(date)
}

fun contractionDuration(contraction: Contraction): String {
    val seconds = contraction.startTime.time - contraction.endTime.time
    return secondToTime(seconds)
}

fun secondToTime(seconds: Long): String {
    val min = ((seconds / 60) % 60)
    val sec = seconds % 60
    return if (sec < 10) {
        "$min:0$sec"
    } else {
        "$min:$sec"
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview(showBackground = true)
fun ContractionScreen(
    viewModel: ContractionsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val contractionUiState by viewModel.displayContractionUiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val recordingContraction: Boolean = false
    var startTime: Date = Date()

    fun onAddButtonClicked() {
        if (recordingContraction) {
            viewModel.contractionUiState.copy(startTime = startTime, endTime = Date())
            coroutineScope.launch {
                viewModel.saveContraction()
            }
        } else {
            startTime = Date()
        }
    }

    Scaffold(floatingActionButton = {
        LargeFloatingActionButton(
            onClick = { onAddButtonClicked() },
            containerColor = if (recordingContraction) {
                Color.Red
            } else {
                MaterialTheme.colorScheme.primaryContainer
            },
            shape = CircleShape,
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add contraction")
        }
    }, floatingActionButtonPosition = FabPosition.Center) {
        if (contractionUiState.contractionList.isNotEmpty()) {
            ContractionBody(sortedContraction = contractionUiState.contractionList.sortedByDescending { it.startTime }, contractionUiState)
        } else {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(32.dp)
            ) {
                Text(text = "Appuyez sur le bouton + quand votre première contraction commence")
            }
        }
    }
}

@Composable
fun ContractionBody(sortedContraction: List<Contraction>, contractionUiState: ContractionUiState) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
        ) {
            Row(modifier = Modifier.fillMaxWidth(.50f), horizontalArrangement = Arrangement.End) {
                Text(text = "Début")
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Durée")
                Spacer(modifier = Modifier.width(24.dp))
            }
            Spacer(modifier = Modifier.fillMaxWidth(.14f))
            Text(
                text = "Fréquence",
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.fillMaxWidth()
            )
        }
        sortedContraction.forEachIndexed { index, contraction ->
            var timeBetwinContractionDuration: Long? = null
            if (index > 0) {
                timeBetwinContractionDuration =
//                    ChronoUnit.SECONDS.between(
//                        contraction.startTime,
//                        sortedContraction[index - 1].startTime
//                    )
                    contraction.startTime.time - sortedContraction[index - 1].startTime.time
            }
            if (index != 0) {
                TimeBetwinContractions(
                    timeBetwinContractionDuration = timeBetwinContractionDuration!!
                )
            }
            contractionNumber = contractionUiState .indexOf(contraction) + 1
            ContractionRow(contraction, contractionNumber = )
        }
    }
}

@Composable
fun ContractionRow(
    contraction: Contraction,
    contractionNumber : Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(.5f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Text(text = formatedHour(contraction.startTime))
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = contractionDuration(contraction),
            fontStyle = FontStyle.Italic,
        )
        Spacer(modifier = Modifier.width(24.dp))
        val color1 = android.graphics.Color.argb(
            MaterialTheme.colorScheme.secondary.toArgb().alpha,
            MaterialTheme.colorScheme.secondary.toArgb().red,
            MaterialTheme.colorScheme.secondary.toArgb().green,
            MaterialTheme.colorScheme.secondary.toArgb().blue
        )
        val color2 = android.graphics.Color.argb(
            MaterialTheme.colorScheme.primary.toArgb().alpha,
            MaterialTheme.colorScheme.primary.toArgb().red,
            MaterialTheme.colorScheme.primary.toArgb().green,
            MaterialTheme.colorScheme.primary.toArgb().blue
        )
        Text(text = (contractions.indexOf(contraction) + 1).toString(),
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.drawWithCache {
                val brush = Brush.linearGradient(
                    listOf(
                        Color(color1), Color(color2)
                    ), end = Offset(x = 0f, 50f)
                )
                onDrawBehind {
                    drawCircle(
                        brush = brush, radius = 45f,
                    )
                }
            })
    }
}

@Composable
fun TimeBetwinContractions(timeBetwinContractionDuration: Long) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Spacer(modifier = Modifier.fillMaxWidth(.57f))
        Text(
            secondToTime(timeBetwinContractionDuration),
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.Bold,
        )
    }
}