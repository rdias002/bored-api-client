package com.example.boredapiclient.data.local.dao

import androidx.room.*
import com.example.boredapiclient.data.local.entities.ActivityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveActivity(activity:ActivityEntity)

    @Query("SELECT * FROM activities_table")
    fun getSavedActivities(): Flow<List<ActivityEntity>>

    @Query("SELECT * FROM activities_table WHERE _id = :id")
    fun getSavedActivity(id: Long): Flow<ActivityEntity>

    @Query("DELETE FROM activities_table")
    suspend fun deleteAllSavedActivities()

    @Query("DELETE FROM activities_table WHERE _id = :id")
    suspend fun deleteSavedActivity(id: Long)

    @Delete
    suspend fun deleteSavedActivity(activity: ActivityEntity)

}