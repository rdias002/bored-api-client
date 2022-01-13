package com.example.boredapiclient.data

import com.example.boredapiclient.data.local.entities.ActivityEntity
import com.example.boredapiclient.data.remote.dto.ActivityResponseDto
import com.example.boredapiclient.domain.model.ActivityModel

object MappingExtensions {
    fun ActivityResponseDto.toActivityEntity(): ActivityEntity = ActivityEntity(
        key?:0,
        activity?:"",
        accessibility?:0.0,
        type?:"",
        participants?:0,
        price?:0.0,
        link?:"",
    )

    fun ActivityResponseDto.toActivityModel(): ActivityModel = ActivityModel(
        key?:0,
        activity?:"",
        accessibility?:0.0,
        type?:"",
        participants?:0,
        price?:0.0,
        link?:"",
        error
    )

    fun ActivityModel.toActivityEntity(): ActivityEntity = ActivityEntity(
        id, activity, accessibility, type, participants, price, link
    )

    fun ActivityEntity.toActivityModel(): ActivityModel = ActivityModel(
        _id, activity, accessibility, type, participants, price, link
    )
}