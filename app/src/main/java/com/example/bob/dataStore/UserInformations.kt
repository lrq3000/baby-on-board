package com.example.bob.dataStore

import java.time.LocalDate

data class UserInformations(
    val userName: String,
    val lastPeriodDate: Long,
    val lastOvulationDate: Long?
)
