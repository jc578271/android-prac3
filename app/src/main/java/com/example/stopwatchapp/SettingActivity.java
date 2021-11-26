package com.example.stopwatchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SettingActivity extends AppCompatActivity {
    public static int SETTINGS_REQUEST = 1234;
    private EditText speedInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        speedInput = findViewById(R.id.speed);
    }

    public void doneClicked(View view) {
        String text = speedInput.getText().toString();
        int speed = Integer.parseInt(text);

        Intent intent = new Intent();
        intent.putExtra("speed", speed);
        setResult(RESULT_OK, intent);
        finish();
    }
}