package com.example.boredapiclient.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "activity_history_table")
data class ActivityHistoryEntity(
    @PrimaryKey
    val _id: Long,
    val activity: String,
    val accessibility: Double,
    val type: String,
    val participants: Int,
    val price: Double,
    val link: String,
    val timeStamp: Date
)
