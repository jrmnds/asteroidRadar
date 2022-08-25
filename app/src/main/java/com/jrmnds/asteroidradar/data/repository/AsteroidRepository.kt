package com.jrmnds.asteroidradar.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jrmnds.asteroidradar.BuildConfig.NASA_KEY
import com.jrmnds.asteroidradar.common.NetworkUtils
import com.jrmnds.asteroidradar.data.NasaApi
import com.jrmnds.asteroidradar.database.NasaDataBase
import com.jrmnds.asteroidradar.domain.model.Asteroid
import com.jrmnds.asteroidradar.domain.model.PictureOfDay
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.Exception

class AsteroidRepository(private val nasaDataBase: NasaDataBase) {

    val TAG = this.javaClass.name

    private lateinit var pictureOfDay: PictureOfDay

    val getAsteroidsList: LiveData<List<Asteroid>> by lazy {
        nasaDataBase.getAsteroidDao().getAllAsteroids()
    }

    val getTodayAsteroidsList: LiveData<List<Asteroid>> by lazy {
        nasaDataBase.getAsteroidDao().getTodayAsteroids(
            LocalDateTime.now().format(DateTimeFormatter.ISO_DATE)
        )
    }

    val getWeekAsteroidsList: LiveData<List<Asteroid>> by lazy {
        nasaDataBase.getAsteroidDao().getAsteroidsWeek(
            startDate = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE),
            endDate = LocalDateTime.now().plusDays(7).format(DateTimeFormatter.ISO_DATE)
        )
    }



    suspend fun getPictureOfTheDay(): PictureOfDay {
        withContext(IO){
            try{
                pictureOfDay = NasaApi.nasaService.getPictureOfTheDay(NASA_KEY)
            }catch (e: Exception){
                Log.e(TAG, e.message!!)
            }
        }
        return pictureOfDay
    }

    suspend fun refreshAsteroidData(){
        withContext(IO){
            try{
                val allAsteroids = NasaApi.nasaService.getAllAsteroids(NASA_KEY)
                val data = NetworkUtils.parseAsteroidsJsonResult(JSONObject(allAsteroids))
                nasaDataBase.getAsteroidDao().insertAllAsteroids(data)
            }catch (e: Exception){
                Log.e(TAG, e.message!!)
            }
        }
    }

}