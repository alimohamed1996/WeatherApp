package com.example.usecases

import android.app.Application
import android.arch.lifecycle.MutableLiveData

internal val applicationLiveData = MutableLiveData<Application>()

internal fun <T>MutableLiveData<T>.getNonnull() = value!!


object Domain {
    fun integrateWith(application: Application){
        applicationLiveData.value = application

    }
}