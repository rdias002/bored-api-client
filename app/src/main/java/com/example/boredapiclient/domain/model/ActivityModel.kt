package com.example.boredapiclient.domain.model

data class ActivityModel(
    val id: Long,
    val activity: String,
    val accessibility: Double,
    val type: String,
    val participants: Int,
    val price: Double,
    val link: String,
    val markedAsDone: Boolean,
    val error: String? = null
)