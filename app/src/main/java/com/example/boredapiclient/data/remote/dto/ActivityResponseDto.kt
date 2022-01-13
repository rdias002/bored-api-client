package com.example.boredapiclient.data.remote.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ActivityResponseDto(

	@field:SerializedName("key")
	val key: Long? = null,

	@field:SerializedName("activity")
	val activity: String? = null,

	@field:SerializedName("accessibility")
	val accessibility: Double? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("participants")
	val participants: Int? = null,

	@field:SerializedName("price")
	val price: Double? = null,

	@field:SerializedName("link")
	val link: String? = null,

	@field:SerializedName("error")
	val error: String? = null

) : Parcelable
