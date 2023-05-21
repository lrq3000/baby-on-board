package com.example.bob.ui.compose.contractions

import com.example.bob.data.Contraction
import java.time.Duration
import java.time.LocalDateTime

data class ContractionUiState(
    val id: Int = 0,
    val startTime: LocalDateTime = LocalDateTime.now(),
    val contractionDuration: Duration = Duration.ZERO,
)

fun ContractionUiState.toContraction(): Contraction = Contraction(
    id = id,
    startTime = startTime,
    contractionDuration = contractionDuration
)

fun Contraction.toContractionUiState(): ContractionUiState = ContractionUiState(
    id = id,
    startTime = startTime,
    contractionDuration = contractionDuration
)