package com.example.boredapiclient.di

import android.content.Context
import com.example.boredapiclient.data.local.ActivitiesDB
import com.example.boredapiclient.data.local.dao.ActivityDao
import com.example.boredapiclient.data.remote.BoredApi
import com.example.boredapiclient.data.repository.ActivityRepositoryImpl
import com.example.boredapiclient.domain.repository.ActivityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideActivitiesDb(@ApplicationContext context: Context): ActivitiesDB {
        return ActivitiesDB.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideActivityDao(db: ActivitiesDB) = db.activityDao

    @Provides
    @Singleton
    fun provideBoredApi(): BoredApi {
        return Retrofit.Builder()
            .baseUrl(BoredApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BoredApi::class.java)
    }

    @Provides
    @Singleton
    fun provideActivityRepository(api: BoredApi, dao: ActivityDao): ActivityRepository =
        ActivityRepositoryImpl(api, dao)
}