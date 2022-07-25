package com.jrmnds.asteroidradar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jrmnds.asteroidradar.database.dao.NasaDao
import com.jrmnds.asteroidradar.database.entities.AsteroidEntity
import com.jrmnds.asteroidradar.database.entities.PictureOfTheDayEntity


object NasaDataBase {

    private lateinit var INSTANCE: NasaDataBase

    @Database(entities = [AsteroidEntity::class, PictureOfTheDayEntity::class], version = 1)
    abstract class NasaDataBase: RoomDatabase(){
        abstract val nasaDao: NasaDao
    }

    fun getDataBase(context: Context): NasaDataBase{
        synchronized(NasaDataBase::class.java){
            if(!::INSTANCE.isInitialized){
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                NasaDataBase::class.java,
                "nasaDataBase").build()
            }
        }
        return INSTANCE
    }
}