package com.example.boredapiclient.data

import com.example.boredapiclient.data.local.entities.SavedActivityEntity
import com.example.boredapiclient.data.remote.dto.ActivityResponseDto
import com.example.boredapiclient.domain.model.ActivityModel

object MappingExtensions {
    fun ActivityResponseDto.toActivityEntity(): SavedActivityEntity = SavedActivityEntity(
        key ?: 0,
        activity ?: "",
        accessibility ?: 0.0,
        type ?: "",
        participants ?: 0,
        price ?: 0.0,
        link ?: "",
        false
    )

    fun ActivityResponseDto.toActivityModel(): ActivityModel = ActivityModel(
        key ?: 0,
        activity ?: "",
        accessibility ?: 0.0,
        type ?: "",
        participants ?: 0,
        price ?: 0.0,
        link ?: "",
        false
    )

    fun ActivityModel.toActivityEntity(): SavedActivityEntity = SavedActivityEntity(
        id, activity, accessibility, type, participants, price, link, markedAsDone
    )

    fun SavedActivityEntity.toActivityModel(): ActivityModel = ActivityModel(
        _id, activity, accessibility, type, participants, price, link, markedAsDone
    )
}