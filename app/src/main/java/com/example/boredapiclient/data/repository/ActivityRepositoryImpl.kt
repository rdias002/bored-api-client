package com.example.boredapiclient.data.repository

import com.example.boredapiclient.data.MappingExtensions.toActivityEntity
import com.example.boredapiclient.data.MappingExtensions.toActivityModel
import com.example.boredapiclient.data.local.dao.ActivityDao
import com.example.boredapiclient.data.remote.BoredApi
import com.example.boredapiclient.domain.model.ActivityModel
import com.example.boredapiclient.domain.repository.ActivityRepository
import core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class ActivityRepositoryImpl(
    private val api: BoredApi,
    private val dao: ActivityDao
) : ActivityRepository {
    override fun getRandomActivity(): Flow<Resource<ActivityModel>> = flow {
        emit(Resource.Loading())
        try {
            val activityModel = api.getRandomActivity().toActivityModel()
            emit(Resource.Success(activityModel))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error("Oops, something went wrong"))
        }
    }

    override fun getActivityWithFilters(
        type: String,
        participants: Int,
        minPrice: Double,
        maxPrice: Double
    ): Flow<Resource<ActivityModel>> = flow {
        emit(Resource.Loading())
        try {
            val activityModel = api.getActivityWithPriceRange(
                type.takeIf { it.isNotBlank() },
                participants.takeIf { it >= 0 },
                minPrice.takeIf { it < maxPrice && minPrice >= 0.0 },
                maxPrice.takeIf { it > minPrice && maxPrice <= 1.0 }
            ).toActivityModel()
            emit(Resource.Success(activityModel))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error("Oops, something went wrong"))
        }
    }

    override fun saveActivity(activity: ActivityModel): Flow<Resource<String>> = flow {
        dao.saveActivity(activity.toActivityEntity())
        emit(Resource.Success("Activity saved successfully"))
    }

    override fun getSavedActivities(): Flow<List<ActivityModel>> =
        dao.getSavedActivities().map { entities ->
            entities.map { it.toActivityModel() }
        }

    override fun getSavedActivity(activityModel: ActivityModel): Flow<ActivityModel> =
        dao.getSavedActivity(activityModel.id).map {
            it.toActivityModel()
        }

    override fun deleteSavedActivity(activityModel: ActivityModel): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        dao.deleteSavedActivity(activityModel.toActivityEntity())
        emit(Resource.Success("Successfully deleted this activity"))
    }


}