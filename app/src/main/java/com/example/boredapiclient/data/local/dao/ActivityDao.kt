package com.example.boredapiclient.data.local.dao

import androidx.room.*
import com.example.boredapiclient.data.local.entities.SavedActivityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveActivity(savedActivity: SavedActivityEntity)

    @Query("SELECT * FROM saved_activities_table")
    fun getSavedActivities(): Flow<List<SavedActivityEntity>>

    @Query("SELECT * FROM saved_activities_table WHERE _id = :id")
    fun getSavedActivity(id: Long): Flow<SavedActivityEntity>

    @Query("DELETE FROM saved_activities_table")
    suspend fun deleteAllSavedActivities()

    @Query("DELETE FROM saved_activities_table WHERE _id = :id")
    suspend fun deleteSavedActivity(id: Long)

    @Query("UPDATE saved_activities_table SET markedAsDone = :isMarkedAsDone WHERE _id = :id")
    suspend fun markActivityAsDone(id: Long, isMarkedAsDone: Boolean = true)

    @Delete
    suspend fun deleteSavedActivity(savedActivity: SavedActivityEntity)

}