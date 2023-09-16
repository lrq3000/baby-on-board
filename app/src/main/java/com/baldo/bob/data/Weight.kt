package com.baldo.bob.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.Date

@Entity(tableName = "weights")
data class Weight(
    @PrimaryKey
    val date: Date,
    val weight: Float
)
