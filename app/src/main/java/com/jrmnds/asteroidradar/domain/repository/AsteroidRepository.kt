package com.jrmnds.asteroidradar.domain.repository

import com.jrmnds.asteroidradar.domain.model.AsteroidsList
import com.jrmnds.asteroidradar.domain.model.PictureOfDay

interface AsteroidRepository {

    suspend fun getAllAsteroids(apiKey: String) : AsteroidsList

    suspend fun getPictureOfTheDay(apiKey: String): PictureOfDay

}