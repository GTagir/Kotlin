package com.example.kotlinweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinweather.model.Repository
import com.example.kotlinweather.model.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(
    private val lifeDataToObserve : MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl : Repository = RepositoryImpl()
) : ViewModel() {

    fun getLiveData() = lifeDataToObserve

    fun getWeather() = getDataFromLocalSource()

    private fun getDataFromLocalSource(){
        lifeDataToObserve.value = AppState.Loading

        Thread {
            sleep(2000)

            lifeDataToObserve.postValue(
                AppState.Success(repositoryImpl.getWeatherFromLocalStorage()))
        }.start()
    }
}