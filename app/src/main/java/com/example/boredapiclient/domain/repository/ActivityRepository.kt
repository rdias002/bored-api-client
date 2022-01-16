package com.example.boredapiclient.domain.repository

import com.example.boredapiclient.core.utils.Resource
import com.example.boredapiclient.domain.model.ActivityModel
import kotlinx.coroutines.flow.Flow

interface ActivityRepository {
    fun getRandomActivity(): Flow<Resource<ActivityModel>>
    fun getActivityWithFilters(
        type: String = "",
        participants: Int = 1,
        minPrice: Double = 0.0, maxPrice: Double = 0.0
    ): Flow<Resource<ActivityModel>>
    fun saveActivity(activity: ActivityModel): Flow<Resource<String>>
    fun getSavedActivities(): Flow<List<ActivityModel>>
    fun getSavedActivity(activityModel: ActivityModel): Flow<ActivityModel>
    fun deleteSavedActivity(activityModel: ActivityModel): Flow<Resource<String>>
    fun markActivityAsDone(activityModel: ActivityModel): Flow<Resource<String>>
}