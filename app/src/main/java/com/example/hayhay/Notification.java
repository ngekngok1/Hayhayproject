package com.example.hayhay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

public class Notification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            // Load data or perform heavy operations here
            // Example: fetchData();
            // ...
        }, 1000); // Delay of 1 second (remove this in actual implementation)
    }
}