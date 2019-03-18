package com.waether.app.features.randomizer

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.usecases.Ticker
import com.example.usecases.randomNumberGenerator

class RandomizerViewModel : ViewModel(){

    private val ticker = InitTicker()

    val randomNumber = MutableLiveData<Int>()

    init {
        randomNumber.postValue(randomNumberGenerator().toInt())
        ticker.start()
    }


    private fun InitTicker(): Ticker {
        return Ticker {
            randomNumber.postValue(randomNumberGenerator().toInt())

        }
    }
    override fun onCleared() {
        super.onCleared()
        ticker.cancel()
    }

}
