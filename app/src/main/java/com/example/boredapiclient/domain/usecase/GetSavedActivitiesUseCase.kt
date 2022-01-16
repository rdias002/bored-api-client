package com.example.boredapiclient.domain.usecase

import com.example.boredapiclient.domain.repository.ActivityRepository
import javax.inject.Inject

class GetSavedActivitiesUseCase @Inject constructor(
    private val repository: ActivityRepository
) {
    operator fun invoke() = repository.getSavedActivities()
}