package com.example.sdy60_ge2_g1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HydrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_hydration);

        SharedPreferences prefs =
                getSharedPreferences("user_data", MODE_PRIVATE);

        float weight = prefs.getFloat("weight", 70);

        float temp = 28;
        float hum = 45;

        float water = 35 * weight;
        if (temp > 25) water += (temp - 25) * 150;
        if (hum < 50) water += (50 - hum) * 10;

        ((TextView)findViewById(R.id.txtHydration))
                .setText("Recommended intake:\n≈ " +
                        String.format("%.1f", water/1000) + " L/day");
    }
}
