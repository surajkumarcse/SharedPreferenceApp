package com.example.sharedprefapplication.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "key_value_table")
data class KeyValue(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val key: String,
    var value: String

)