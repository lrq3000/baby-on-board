package com.example.bob.data

import androidx.room.Entity

@Entity(tableName = "informations")
data class Informations(
    val name: String,
    val lastPeriodes: String,
    val lastOvulation: String?
)