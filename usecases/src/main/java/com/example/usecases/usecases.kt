package com.example.usecases

import android.arch.lifecycle.MutableLiveData

fun randomNumberGenerator() = Math.random() * 1000

fun incrementNumber(incrementValue: Int = 1 , mutableLiveData: MutableLiveData<Int>){
    val oldValue = mutableLiveData.value ?: 0
    mutableLiveData.postValue(oldValue + incrementValue)

}