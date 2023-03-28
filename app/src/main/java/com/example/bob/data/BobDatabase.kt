package com.example.bob.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Informations::class], version = 1, exportSchema = false)
abstract class BobDatabase : RoomDatabase() {
    abstract fun informationsDao(): InformationsDao

    companion object {
        @Volatile
        private var Instance: BobDatabase? = null
        fun getDatabase(context: Context): BobDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, BobDatabase::class.java, "informations_database")
                    .fallbackToDestructiveMigration().build().also { Instance = it }
            }
        }
    }
}