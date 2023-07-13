package com.erif.contentloader.helper.timer;

import android.os.CountDownTimer;

public class Delay {

    private final int duration;
    private final DelayCallback callback;

    public Delay(int seconds, DelayCallback callback) {
        this.duration = seconds * 1000;
        this.callback = callback;
    }

    public Delay(double seconds, DelayCallback callback) {
        this.duration = (int) (seconds * 1000);
        this.callback = callback;
    }

    public void start() {
        Timer timer = new Timer(duration, 1000, callback);
        timer.start();
    }

    private static class Timer extends CountDownTimer {

        private final DelayCallback callback;

        public Timer(long millisInFuture, long countDownInterval, DelayCallback callback) {
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
