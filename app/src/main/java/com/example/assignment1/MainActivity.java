package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    //private BroadcastReceiver broadcast;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButton = findViewById(R.id.createAccount);
    }

    @Override
    protected void onResume() {
        super.onResume();

        imageButton.setOnClickListener(v -> {
            Intent newActivity = new Intent(getBaseContext(), CreateAccount.class);
            startActivity(newActivity);
        });
    }
}