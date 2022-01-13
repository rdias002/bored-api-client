package com.example.boredapiclient.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activities_table")
data class ActivityEntity(
    @PrimaryKey(autoGenerate = true)
    val _id: Long,
    val activity: String,
    val accessibility: Double,
    val type: String,
    val participants: Int,
    val price: Double,
    val link: String
)