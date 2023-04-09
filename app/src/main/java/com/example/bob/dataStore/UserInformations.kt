package com.example.bob.dataStore

import java.time.LocalDate

data class UserInformations(
    val userName: String,
    val lastPeriodDate: String,
    val lastOvulationDate: String?
)
