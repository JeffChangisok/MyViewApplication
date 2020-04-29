package com.example.myviewapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BaseDrawActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_draw);
    }
}
