package com.waether.app

import android.app.Application
import com.example.usecases.Domain


class WeatherApplication : Application(){
    override fun onCreate() {
        super.onCreate()

     Domain.integrateWith(this)
    }
}