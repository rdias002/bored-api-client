package com.example.boredapiclient.domain.usecase

import com.example.boredapiclient.domain.model.ActivityModel
import com.example.boredapiclient.domain.repository.ActivityRepository
import javax.inject.Inject

class DeleteSavedActivityUseCase @Inject constructor(
    private val repository: ActivityRepository
) {
    operator fun invoke(activityModel: ActivityModel) =
        repository.deleteSavedActivity(activityModel)
}