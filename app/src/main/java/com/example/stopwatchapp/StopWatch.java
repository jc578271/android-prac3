package com.example.stopwatchapp;

import androidx.annotation.NonNull;

import java.util.Formatter;
import java.util.Locale;

public class StopWatch {
    private int hours, seconds, minutes;

    StopWatch() {
        hours = minutes = seconds = 0;
    }

    StopWatch(String string) {
        hours = Integer.parseInt(string.substring(0, 2));
        minutes = Integer.parseInt(string.substring(3, 5));
        seconds = Integer.parseInt(string.substring(6, 8)); // 00:00:00
    }

    void tick() {
        seconds++;
        if (seconds > 59) {
            minutes++;
            seconds = 0;
        }
        if (minutes > 59) {
            hours++;
            minutes = 0;
        }
    }


    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb, Locale.getDefault());
        formatter.format("%02d:%02d:%02d", hours, minutes, seconds);
        return formatter.toString();
    }
}
