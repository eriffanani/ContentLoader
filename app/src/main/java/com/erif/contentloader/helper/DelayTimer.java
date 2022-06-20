package com.erif.contentloader.helper;

import android.os.CountDownTimer;

public class DelayTimer {

    private final int duration;
    private final DelayTimerCallback callback;

    public DelayTimer(int seconds, DelayTimerCallback callback) {
        this.duration = seconds * 1000;
        this.callback = callback;
    }

    public void start() {
        Timer timer = new Timer(duration, 1000, callback);
        timer.start();
    }

    private static class Timer extends CountDownTimer {

        private final DelayTimerCallback callback;

        public Timer(long millisInFuture, long countDownInterval, DelayTimerCallback callback) {
            super(millisInFuture, countDownInterval);
            this.callback = callback;
        }

        @Override
        public void onTick(long l) {

        }

        @Override
        public void onFinish() {
            callback.onFinish();
        }
    }



}
