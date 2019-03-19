package com.example.usecases

import android.os.CountDownTimer
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

private const val FINISH_AFTER_MILLIS = 10 * 1000 * 60L
private const val INTERVAL_IN_MILLIS = 1 * 1000L

class Ticker (private val onTicking: (Long) -> Unit)
    : CountDownTimer(FINISH_AFTER_MILLIS, INTERVAL_IN_MILLIS){
    override fun onFinish() {
    }

    override fun onTick(millisUntilFinished: Long) {
        onTicking(millisUntilFinished)
    }
}

fun tickerObserval( onTicking: (Long) -> Unit)  {
    Observable.intervalRange(0L,10L,0L , 1L , TimeUnit.SECONDS)
    //Observable.interval(2,TimeUnit.MILLISECONDS)
        .subscribeOn(AndroidSchedulers.mainThread())
        .observeOn(Schedulers.io())
        .subscribe {
        Log.d("Interval" , it.toString())
        onTicking(it)
    }
}