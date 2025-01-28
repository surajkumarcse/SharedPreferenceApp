package com.example.sharedprefapplication.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [KeyValue::class], version = 1, exportSchema = false)
abstract class SharedPrefDb : RoomDatabase() {
    abstract fun keyValueDao(): KeyValueDao

    companion object {
        @Volatile
        private var INSTANCE: SharedPrefDb? = null

        fun getDatabase(context: Context): SharedPrefDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SharedPrefDb::class.java,
                    "sharedPref_Database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}