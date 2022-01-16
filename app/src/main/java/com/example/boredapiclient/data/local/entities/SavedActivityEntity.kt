package com.example.boredapiclient.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_activities_table")
data class SavedActivityEntity(
    @PrimaryKey(autoGenerate = true)
    val _id: Long,
    val activity: String,
    val accessibility: Double,
    val type: String,
    val participants: Int,
    val price: Double,
    val link: String,
    val markedAsDone: Boolean
)