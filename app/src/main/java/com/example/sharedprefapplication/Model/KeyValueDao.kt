package com.example.sharedprefapplication.Model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface KeyValueDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(keyValue: KeyValue)

    // Retrieve
    @Query("SELECT value FROM key_value_table where `key` = :key ")
    suspend fun retrieve(key: String): String

    @Update
    suspend fun update(keyValue: KeyValue)

    @Delete
    suspend fun delete(keyValue: KeyValue)
}