package com.jrmnds.asteroidradar.data

import com.jrmnds.asteroidradar.domain.model.PictureOfDay
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApiService {

    @GET("neo/rest/v1/feed")
    suspend fun getAllAsteroids(@Query("api_key") nasaApiKey: String): String

    @GET("planetary/apod")
    suspend fun getPictureOfTheDay(@Query("api_key") nasaApiKey: String): PictureOfDay
}