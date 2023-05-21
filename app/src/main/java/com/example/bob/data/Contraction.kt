package com.example.bob.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Duration
import java.time.LocalDateTime
import java.util.Date

@Entity(tableName = "contractions")
data class Contraction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val startTime: LocalDateTime,
    val contractionDuration: Duration,
)
