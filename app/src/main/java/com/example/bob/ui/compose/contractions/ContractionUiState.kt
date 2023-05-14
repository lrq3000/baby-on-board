package com.example.bob.ui.compose.contractions

import com.example.bob.data.Contraction
import java.util.Date

data class ContractionUiState(
    val id: Int = 0,
    val startTime: Date = Date(),
    val endTime: Date = Date(),
)

fun ContractionUiState.toContraction(): Contraction = Contraction(
    id = id,
    startTime = startTime,
    endTime = endTime
)

fun Contraction.toContractionUiState(): ContractionUiState = ContractionUiState(
    id = id,
    startTime = startTime,
    endTime = endTime
)