package com.example.ecosystemalpha.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [BinQueryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class QueriesDatabase : RoomDatabase() {
    abstract fun binQueriesDao(): BinQueriesDao
}