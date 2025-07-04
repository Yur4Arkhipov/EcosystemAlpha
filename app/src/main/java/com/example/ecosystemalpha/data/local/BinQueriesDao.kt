package com.example.ecosystemalpha.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BinQueriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(query: BinQueryEntity)

    @Query("select * from queries")
    fun getAllQueries(): Flow<List<BinQueryEntity>>
}