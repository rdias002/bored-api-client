package com.example.boredapiclient.domain.usecase

import com.example.boredapiclient.core.utils.Resource
import com.example.boredapiclient.domain.model.ActivityModel
import com.example.boredapiclient.domain.repository.ActivityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRandomActivityUseCase @Inject constructor(
    private val repository: ActivityRepository
) {
    operator fun invoke(): Flow<Resource<ActivityModel>> = repository.getRandomActivity()
}