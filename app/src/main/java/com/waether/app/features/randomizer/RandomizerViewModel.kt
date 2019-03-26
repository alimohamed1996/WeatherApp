package com.waether.app.features.randomizer

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.usecases.*
import com.example.usecases.engine.Ticker
import com.example.usecases.engine.tickerObserval


private const val DEFAULT_VALUE = 0
private const val DEFAULT_STATE_VALUE = false

class RandomizerViewModel : ViewModel(){


    private val ticker = InitTicker()

    val randomNumber = MutableLiveData<Int>()
    val numberLiveData = MutableLiveData<Int>()
    val stateLiveData = MutableLiveData<Boolean>()
    init {
        randomNumber.postValue(DEFAULT_VALUE)
        numberLiveData.postValue(DEFAULT_VALUE)
        stateLiveData.postValue(DEFAULT_STATE_VALUE)
        //ticker.start()
//        tickerObserval {
//            randomNumber.postValue(randomNumberGenerator().toInt())
//        }
    }
    fun stateSwitch(){
        switchState(stateLiveData)
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
