package com.example.stopwatchapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private StopWatch stopWatch;
    private Handler handler;
    private boolean isRunning;
    private TextView display;
    private Button toggle;
    private int speed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = (TextView) findViewById(R.id.display);
        toggle = (Button) findViewById(R.id.startButton);
        isRunning = false;
        if (savedInstanceState == null) {
            stopWatch = new StopWatch();
        } else {
            stopWatch = new StopWatch(savedInstanceState.getString("value"));
            boolean running = savedInstanceState.getBoolean("running");
            speed = savedInstanceState.getInt("speed");
            display.setText(stopWatch.toString());
            if (running) {
                enableStopWatch();
                toggle.setText("Stop");
            }
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("value", stopWatch.toString());
        outState.putBoolean("running", isRunning);
        outState.putInt("speed", speed);
    }

    private void enableStopWatch() {
        isRunning = true;
        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (isRunning) {
                    stopWatch.tick();
                    display.setText(stopWatch.toString());
                    handler.postDelayed(this, speed);
                }
            }
        });
    }

    public void disableStopWatch() {
        isRunning = false;
    }
    public void startClicked(View v) {
        toggle = (Button) v;
        if (isRunning) {
            disableStopWatch();
            toggle.setText("Start");
        } else {
            enableStopWatch();
            toggle.setText("Stop");
        }
    }

    public void settingsClicked(View view) {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivityForResult(intent, SettingActivity.SETTINGS_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SettingActivity.SETTINGS_REQUEST) {
            if (resultCode == RESULT_OK) {
                assert data != null;
                speed = data.getIntExtra("speed", 1000);
            }
        }
    }
}