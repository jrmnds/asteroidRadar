package com.jrmnds.asteroidradar.api.network

import com.jrmnds.asteroidradar.domain.Asteroid
import com.jrmnds.asteroidradar.domain.PictureOfDay
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApiServices {

    @GET("neo/rest/v1/feed")
    suspend fun getAllAsteroids(@Query("api_key") nasaApiKey: String): Asteroid

    @GET("planetary/apod")
    suspend fun getPictureOftheDay(@Query("api_key") nasaApiKey: String): PictureOfDay
}