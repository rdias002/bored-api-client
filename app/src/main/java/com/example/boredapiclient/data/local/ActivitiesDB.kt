package com.example.boredapiclient.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.boredapiclient.data.local.dao.ActivityDao
import com.example.boredapiclient.data.local.entities.ActivityEntity

@Database(
    entities = [ActivityEntity::class],
    version = 1
)
abstract class ActivitiesDB : RoomDatabase() {
    abstract val activityDao: ActivityDao

    companion object {
        @Volatile
        private var INSTANCE: ActivitiesDB? = null

        fun getInstance(context: Context): ActivitiesDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ActivitiesDB::class.java,
                        "activities_db"
                    ).build()
                }
                return instance
            }
        }
    }
}