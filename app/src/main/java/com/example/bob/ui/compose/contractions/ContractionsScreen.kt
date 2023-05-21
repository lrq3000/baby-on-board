package com.example.bob.ui.compose.contractions

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.material.icons.filled.HourglassBottom
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.unit.sp
import androidx.core.graphics.alpha
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bob.data.Contraction
import com.example.bob.ui.AppViewModelProvider
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.temporal.ChronoUnit
import java.util.Locale

fun formatedHour(date: LocalDateTime): String {
//    return DateFormat.getTimeInstance(DateFormat.SHORT, Locale.FRANCE).format(date)
    return DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.FRANCE)
        .format(date)
}

fun contractionDuration(contraction: Contraction): String {
    val seconds = contraction.contractionDuration.seconds
    return secondToTime(seconds)
}

fun secondToTime(seconds: Long): String {
    Log.e("second", seconds.toString())
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
    val recordingContraction = remember { mutableStateOf(false) }
    var startTime by remember { mutableStateOf(LocalDateTime.now()) }
    val onValueChange = viewModel::updateUiState

    fun onAddButtonClicked() {
        if (recordingContraction.value) {
            onValueChange(
                viewModel.contractionUiState.copy(
                    startTime = startTime,
                    contractionDuration = Duration.between(startTime, LocalDateTime.now())
                )
            )
            coroutineScope.launch {
                viewModel.saveContraction()
            }
            recordingContraction.value = false
        } else {
            startTime = LocalDateTime.now()
            recordingContraction.value = true
        }
    }

    Scaffold(floatingActionButton = {
        LargeFloatingActionButton(
            onClick = { onAddButtonClicked() },
            containerColor = if (recordingContraction.value) {
                MaterialTheme.colorScheme.error
            } else {
                MaterialTheme.colorScheme.primaryContainer
            },
            shape = CircleShape,
        ) {
            if (recordingContraction.value) {
                Icon(
                    imageVector = Icons.Filled.HourglassBottom,
              contentDescription = "End contraction"
            )
                //                   Text(text = Duration.between(startTime, LocalDateTime.now()).toString())
            } else {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add contraction")
            }
        }
    }, floatingActionButtonPosition = FabPosition.Center) {
        if (contractionUiState.contractionList.isNotEmpty()) {
            ContractionBody(
                sortedContraction = contractionUiState.contractionList.sortedByDescending { it.id },
                contractionUiState.contractionList
            )
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
fun ContractionBody(sortedContraction: List<Contraction>, contractions: List<Contraction>) {
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
                Spacer(modifier = Modifier.width(34.dp))
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
                    ChronoUnit.SECONDS.between(
                        contraction.startTime,
                        sortedContraction[index - 1].startTime
                    )
//                contraction.startTime.second.toLong() - sortedContraction[index - 1].startTime.second.toLong()
            }
            if (index != 0) {
                TimeBetwinContractions(
                    timeBetwinContractionDuration = timeBetwinContractionDuration!!
                )
            }
            ContractionRow(contraction, contractionNumber = contractions.indexOf(contraction) + 1)
        }
    }
}

@Composable
fun ContractionRow(
    contraction: Contraction,
    contractionNumber: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(.5f),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = formatedHour(contraction.startTime),
            color = Color.Gray,
            fontSize = 10.sp
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = contractionDuration(contraction),
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Medium
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
        Text(text = contractionNumber.toString(),
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
            secondToTime(timeBetwinContractionDuration.toLong()),
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.ExtraBold,
        )
    }
}