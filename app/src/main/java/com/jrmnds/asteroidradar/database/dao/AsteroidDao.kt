package com.jrmnds.asteroidradar.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jrmnds.asteroidradar.domain.model.Asteroid
import com.jrmnds.asteroidradar.domain.model.PictureOfDay

@Dao
interface AsteroidDao {

    @Query("SELECT * FROM Asteroid ORDER BY date(closeApproachDate) DESC")
    fun getAllAsteroids(): LiveData<List<Asteroid>>


    @Query("SELECT * FROM Asteroid WHERE closeApproachDate = :date ORDER BY date(closeApproachDate) ASC ")
    fun getTodayAsteroids(date: String): LiveData<List<Asteroid>>

    @Query("SELECT * FROM Asteroid WHERE closeApproachDate BETWEEN :startDate AND :endDate ORDER BY closeApproachDate ASC")
    fun getAsteroidsWeek(startDate: String, endDate: String): LiveData<List<Asteroid>>

    @Transaction
    fun updateAsteroidData(asteroids: List<Asteroid>): List<Long> {
        deleteAll()
        return insertAllAsteroids(asteroids)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAsteroids(asteroids: List<Asteroid>): List<Long>

    @Query("DELETE FROM Asteroid")
    fun deleteAll()
}