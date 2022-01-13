package com.example.boredapiclient.data.remote

import com.example.boredapiclient.data.remote.dto.ActivityResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BoredApi {

    @GET("activity/")
    suspend fun getRandomActivity(): ActivityResponseDto

    @GET("activity")
    suspend fun getActivityWithPriceRange(
        @Query("type") type: String?,
        @Query("participants") participants: Int?,
        @Query("minprice") minPrice: Double?,
        @Query("maxprice") maxPrice: Double?,
    ): ActivityResponseDto

    companion object{
        const val BASE_URL = "https://www.boredapi.com/api/"
    }
}