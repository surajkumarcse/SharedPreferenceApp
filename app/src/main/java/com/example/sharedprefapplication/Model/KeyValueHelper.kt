package com.example.sharedprefapplication.Model

import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class KeyValueHelper {
    companion object {
        // create
        fun createKeyValuePair(context: Context, keyValue: KeyValue) {
            val db = SharedPrefDb.getDatabase(context)
            val keyValueDao = db.keyValueDao()

            GlobalScope.launch {
                keyValueDao.create(keyValue)
            }
        }

        // Retrieve
        suspend fun retrieveKeyValuePair(context: Context, key: String): String {
            val db = SharedPrefDb.getDatabase(context)
            val keyValueDao = db.keyValueDao()

            val value = keyValueDao.retrieve(key);

            if(value == null) throw Exception("no such key found")

            return value;
        }

//        // Update
//        suspend fun updateKeyValuePair(context: Context, key: String) {
//            val db = SharedPrefDb.getDatabase(context)
//            val keyValueDao = db.keyValueDao()
//
//            keyValueDao.update(key);
//        }

        // Delete
        fun deleteKeyValuePair(context: Context, keyValue: KeyValue) {
            val db = SharedPrefDb.getDatabase(context)
            val keyValueDao = db.keyValueDao()

            GlobalScope.launch {

                keyValueDao.delete(keyValue);
            }
        }
    }
}