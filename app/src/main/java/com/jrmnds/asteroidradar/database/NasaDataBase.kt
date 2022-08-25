package com.jrmnds.asteroidradar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jrmnds.asteroidradar.database.dao.AsteroidDao
import com.jrmnds.asteroidradar.domain.model.Asteroid
import com.jrmnds.asteroidradar.domain.model.PictureOfDay

@Database(entities = [Asteroid::class], version = 1, exportSchema = false)
abstract class NasaDataBase: RoomDatabase() {

    abstract fun getAsteroidDao(): AsteroidDao

    companion object{
        private lateinit var INSTANCE: NasaDataBase

        fun getInstance(context: Context): NasaDataBase{
            synchronized(NasaDataBase::class.java){
                if(!::INSTANCE.isInitialized){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        NasaDataBase::class.java,
                        "nasaDatabase").build()
                }
            }
            return INSTANCE
        }
    }

}