package com.baldo.bob.ui.compose.weight.viewModel

import com.baldo.bob.data.Weight
import java.util.Date

data class WeightUiState(
    val date: Date = Date(),
    val weight: Float = 0F
)

fun WeightUiState.toWeight(): Weight = Weight(
    date = date,
    weight = weight
)

fun Weight.toWeightuiState(): WeightUiState = WeightUiState(
    date = date,
    weight = weight
)

fun WeightUiState.isValid(): Boolean {
    return weight > 25F
}