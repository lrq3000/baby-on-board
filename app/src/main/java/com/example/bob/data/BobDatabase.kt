package com.example.bob.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.Date

@Database(entities = [Note::class], version = 4, exportSchema = false)
@TypeConverters(Converters::class)
abstract class BobDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao

    companion object {
        @Volatile
        private var Instance: BobDatabase? = null
        fun getDatabase(context: Context): BobDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, BobDatabase::class.java, "notes_database")
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
}
