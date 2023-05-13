package com.example.bob.ui.compose.contractions

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.alpha
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.temporal.ChronoUnit

val contractions = listOf<Contraction>(
    Contraction(LocalDateTime.now(), LocalDateTime.now().plusSeconds(83)),
    Contraction(
        LocalDateTime.now().plusMinutes(4),
        LocalDateTime.now().plusMinutes(4).plusSeconds(45)
    ),
    Contraction(
        LocalDateTime.now().plusMinutes(6).plusSeconds(37),
        LocalDateTime.now().plusMinutes(6).plusSeconds(37).plusSeconds(35)
    )
)

fun formatedHour(date: LocalDateTime): String {
    return DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).format(date)
}

fun contractionDuration(contraction: Contraction): String {
    val seconds = ChronoUnit.SECONDS.between(contraction.startTime, contraction.endTime)
    val min = ((seconds / 60) % 60)
    val sec = seconds % 60
    return if (sec < 10) {
        "$min:0$sec"
    } else {
        "$min:$sec"
    }
}

@Composable
@Preview(showBackground = true)
fun ContractionScreen() {
    val sortedContraction = contractions.sortedByDescending { it.startTime }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp)
    ) {
        sortedContraction.forEachIndexed { index, contraction ->
            var timeBetwinContractionDuration: Long? = null
            if (index > 0) {
                timeBetwinContractionDuration =
                    ChronoUnit.SECONDS.between(
                        contraction.startTime,
                        sortedContraction[index - 1 ].endTime
                    )
            }
            Log.e("time", timeBetwinContractionDuration.toString())
            ContractionRow(contraction)
            if (index != sortedContraction.lastIndex) {
                TimeBetwinContractions(index.toLong())
            }
        }
    }
}

@Composable
fun ContractionRow(
    contraction: Contraction,
) {
    val brush = Brush.horizontalGradient(listOf(Color.Red, Color.Blue))
    Row(
        modifier = Modifier.fillMaxWidth(.5f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Text(text = formatedHour(contraction.startTime))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = contractionDuration(contraction).toString())
        Spacer(modifier = Modifier.width(24.dp))
        val color1 = android.graphics.Color.argb(
            MaterialTheme.colorScheme.primary.toArgb().alpha,
            MaterialTheme.colorScheme.primary.toArgb().red,
            MaterialTheme.colorScheme.primary.toArgb().green,
            MaterialTheme.colorScheme.primary.toArgb().blue
        )
        val color2 = android.graphics.Color.argb(
            MaterialTheme.colorScheme.secondary.toArgb().alpha,
            MaterialTheme.colorScheme.secondary.toArgb().red,
            MaterialTheme.colorScheme.secondary.toArgb().green,
            MaterialTheme.colorScheme.secondary.toArgb().blue
        )
        Text(text = (contractions.indexOf(contraction) + 1).toString(),
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .drawWithCache {
                    val brush = Brush.linearGradient(
                        listOf(
                            Color(color1), Color(color2)
                        ),
                        end = Offset(x = 0f, 100f)
                    )
                    onDrawBehind {
                        drawCircle(
                            brush = brush, radius = 40f,
                        )
                    }
                })
    }
}

@Composable
fun TimeBetwinContractions(timeBetwinContractionDuration: Long?) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Spacer(modifier = Modifier.fillMaxWidth(.55f))
        Text(timeBetwinContractionDuration.toString())
    }
}