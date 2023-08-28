package com.baldo.bob.dataStore

data class UserInformations(
    val userName: String,
    val lastPeriodDate: Long,
    val lastOvulationDate: Long?
)

data class AppSettings(
    val themeSetting : String
)
