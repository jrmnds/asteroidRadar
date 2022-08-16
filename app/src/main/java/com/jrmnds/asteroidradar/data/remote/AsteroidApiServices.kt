package com.jrmnds.asteroidradar.data.remote

import com.jrmnds.asteroidradar.domain.model.Asteroid
import com.jrmnds.asteroidradar.domain.model.AsteroidsList
import com.jrmnds.asteroidradar.domain.model.PictureOfDay
import retrofit2.http.GET
import retrofit2.http.Query

interface AsteroidApiServices {

    @GET("neo/rest/v1/feed")
    suspend fun getAllAsteroids(@Query("api_key") nasaApiKey: String): AsteroidsList

    @GET("planetary/apod")
    suspend fun getPictureOfTheDay(@Query("api_key") nasaApiKey: String): PictureOfDay
}