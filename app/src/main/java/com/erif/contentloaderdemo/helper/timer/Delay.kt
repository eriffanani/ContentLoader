package com.erif.contentloaderdemo.helper.timer

import android.os.CountDownTimer

class Delay {

    private var duration = 0
    private var callback: DelayCallback? = null

    constructor(seconds: Int, callback: DelayCallback?) {
        duration = seconds * 1000
        this.callback = callback
    }

    constructor(seconds: Double, callback: DelayCallback?) {
        duration = (seconds * 1000).toInt()
        this.callback = callback
    }

    fun start() {
        val timer = Timer(duration.toLong(), 1000, callback)
        timer.start()
    }

    private class Timer(
        millisInFuture: Long,
        countDownInterval: Long,
        private val callback: DelayCallback?
    ) :
        CountDownTimer(millisInFuture, countDownInterval) {
        override fun onTick(l: Long) {}
        override fun onFinish() {
            callback!!.onFinish()
        }
    }

}