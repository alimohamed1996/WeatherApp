package com.waether.app.features.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.entities.City
import com.example.usecases.CitiesResult
import com.example.usecases.SearchCityByNameUseCase
import com.example.usecases.engine.toMutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(
     val searchProgress : MutableLiveData<Boolean> = false.toMutableLiveData(),
    val cityResult: CitiesResult = ArrayList<City>().toMutableLiveData(),
    private val searchCityByName: SearchCityByNameUseCase = SearchCityByNameUseCase(searchProgress,cityResult)
) : ViewModel(){

    fun searchBottonClick(cityName : String?){
        Observable.fromCallable { searchCityByName.invoke(cityName) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }


    override fun onCleared() {
        super.onCleared()
    }
}