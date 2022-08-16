package com.jrmnds.asteroidradar.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jrmnds.asteroidradar.domain.model.AsteroidsList
import com.jrmnds.asteroidradar.domain.model.PictureOfDay

@Dao
interface NasaDao {

    @Query("SELECT * FROM asteroids")
    fun getAllAsteroids(): LiveData<AsteroidsList>

    @Query("SELECT id FROM pictureOfTheDay")
    fun getThePictureOfTheDay(id: Int): LiveData<PictureOfDay>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAsteroid(vararg asteroids: AsteroidsList)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insetPictureOfTheDay(vararg picture: PictureOfDay)

}