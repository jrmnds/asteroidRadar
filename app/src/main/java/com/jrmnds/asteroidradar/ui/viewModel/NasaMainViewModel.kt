package com.jrmnds.asteroidradar.ui.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.jrmnds.asteroidradar.R
import com.jrmnds.asteroidradar.data.repository.AsteroidRepository
import com.jrmnds.asteroidradar.database.NasaDataBase
import com.jrmnds.asteroidradar.domain.model.Asteroid
import com.jrmnds.asteroidradar.domain.model.PictureOfDay
import kotlinx.coroutines.launch

class NasaMainViewModel(application: Application): AndroidViewModel(application) {

    private val nasaDataBase = NasaDataBase.getInstance(application)
    private val asteroidRepository = AsteroidRepository(nasaDataBase)

    private var _selectedOptions = MutableLiveData(application.getString(R.string.all_asteroids))

    private val _pictureOfDay = MutableLiveData<PictureOfDay>()
    val pictureOfDay: LiveData<PictureOfDay> = _pictureOfDay

    private val _goToDetailPage = MutableLiveData<Asteroid>()
    val goToDetailPage: LiveData<Asteroid> = _goToDetailPage


    init {
        viewModelScope.launch {
            asteroidRepository.refreshAsteroidData()
            _pictureOfDay.postValue(asteroidRepository.getPictureOfTheDay())
        }
    }


    val asteroids = _selectedOptions.switchMap { selectedOptions ->
        when(selectedOptions){
            application.getString(R.string.next_week_asteroids) -> asteroidRepository.getWeekAsteroidsList
            application.getString(R.string.today_asteroids) -> asteroidRepository.getTodayAsteroidsList
            else -> asteroidRepository.getAsteroidsList
        }
    }


    fun onClickAsteroid(asteroid: Asteroid){
        _goToDetailPage.value = asteroid
    }

    fun clearGoToDetailPageField(){
        _goToDetailPage.value = null
    }

    fun onSelectedOption(option: String){
        _selectedOptions.postValue(option)
    }


    class Factory(val app: Application): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(NasaMainViewModel::class.java)){
                return NasaMainViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}