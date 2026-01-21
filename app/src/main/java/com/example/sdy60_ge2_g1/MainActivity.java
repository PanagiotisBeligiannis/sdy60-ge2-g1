package com.example.sdy60_ge2_g1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnSettings)
                .setOnClickListener(v ->
                        startActivity(new Intent(this, SettingsActivity.class)));

        findViewById(R.id.btnSensors)
                .setOnClickListener(v ->
                        startActivity(new Intent(this, SensorsActivity.class)));

        findViewById(R.id.btnForYou)
                .setOnClickListener(v ->
                        startActivity(new Intent(this, HydrationActivity.class)));

        findViewById(R.id.btnForYourPlants)
                .setOnClickListener(v ->
                        startActivity(new Intent(this, PlantsActivity.class)));
    }
}
