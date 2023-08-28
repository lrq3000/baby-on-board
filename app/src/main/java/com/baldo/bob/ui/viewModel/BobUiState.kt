package com.baldo.bob.ui.viewModel

data class BobUiState(
    val userName: String = "",
    val userLastPeriodsDate: Long = 0,
    val userLastOvulationDate: Long? = null
)