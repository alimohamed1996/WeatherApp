package com.example.usecases

import android.os.CountDownTimer

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