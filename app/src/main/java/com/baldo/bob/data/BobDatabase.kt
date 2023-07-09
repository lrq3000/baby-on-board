package com.baldo.bob.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Date
import java.util.TimeZone

@Database(entities = [Note::class, Contraction::class], version = 6, exportSchema = false)
@TypeConverters(Converters::class)
abstract class BobDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
    abstract fun contractionDao(): ContractionsDao

    companion object {
        @Volatile
        private var Instance: BobDatabase? = null
        fun getDatabase(context: Context): BobDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, BobDatabase::class.java, "bob_database")
                    .fallbackToDestructiveMigration().build().also { Instance = it }
            }
        }
    }
}


class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun fromTimestampToLocalDateTime(value: Long): LocalDateTime {
        return value.let {
            LocalDateTime.ofInstant(
                Instant.ofEpochSecond(it),
                TimeZone.getDefault().toZoneId()
            )
        }
    }

    @TypeConverter
    fun fromLocalDateTimeToTimestamp(date: LocalDateTime?): Long? {
        return date?.toEpochSecond(ZoneOffset.UTC)?.toLong()
    }

    @TypeConverter
    fun fromLongToDuration(value: Long): Duration {
        return value.let { Duration.ofSeconds(it) }
    }

    @TypeConverter
    fun fromDurationToLong(duration: Duration): Long {
        return duration.toMillis() / 1000
    }
}
