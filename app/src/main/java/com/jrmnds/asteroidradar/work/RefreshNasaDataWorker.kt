package com.jrmnds.asteroidradar.work

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.jrmnds.asteroidradar.data.repository.AsteroidRepository
import com.jrmnds.asteroidradar.database.NasaDataBase
import java.lang.Exception

class RefreshNasaDataWorker(context: Context, parameters: WorkerParameters): CoroutineWorker(context, parameters) {

    private val nasaDataBase = NasaDataBase.getInstance(context)
    private val asteroidRepository = AsteroidRepository(nasaDataBase)

    override suspend fun doWork(): Result {
        return try {
            asteroidRepository.refreshAsteroidData()
            Result.success()
        }catch (e: Exception){
            Result.retry()
        }
    }
}