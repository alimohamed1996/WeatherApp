package com.waether.app.features.randomizer

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.usecases.Ticker
import com.example.usecases.incrementNumber
import com.example.usecases.randomNumberGenerator
import com.example.usecases.tickerObserval

private const val DEFAULT_VALUE = 0

class RandomizerViewModel : ViewModel(){


    private val ticker = InitTicker()

    val randomNumber = MutableLiveData<Int>()
    val numberLiveData = MutableLiveData<Int>()
    init {
        randomNumber.postValue(DEFAULT_VALUE)
        numberLiveData.postValue(DEFAULT_VALUE)
        //ticker.start()
//        tickerObserval {
//            randomNumber.postValue(randomNumberGenerator().toInt())
//        }
    }
    fun increment(){
        incrementNumber( mutableLiveData = numberLiveData)
    }
    fun randomnumber(){
        tickerObserval {
            randomNumber.postValue(randomNumberGenerator().toInt())
        }
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
