package com.jrmnds.asteroidradar.data.repository

import com.jrmnds.asteroidradar.data.remote.AsteroidApiServices
import com.jrmnds.asteroidradar.domain.model.AsteroidsList
import com.jrmnds.asteroidradar.domain.model.PictureOfDay
import com.jrmnds.asteroidradar.domain.repository.AsteroidRepository
import javax.inject.Inject

class AsteroidRepositoryImpl @Inject constructor(private val services: AsteroidApiServices): AsteroidRepository {

    override suspend fun getAllAsteroids(apiKey: String): AsteroidsList {
        return services.getAllAsteroids(apiKey)
    }

    override suspend fun getPictureOfTheDay(apiKey: String): PictureOfDay {
        return services.getPictureOfTheDay(apiKey)
    }
}