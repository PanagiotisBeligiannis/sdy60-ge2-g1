package com.example.sdy60_ge2_g1;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PlantsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_plants);

        float ET = 5.0f;
        float precipitation = 1.0f;
        float gwr = 1.0f;

        float irrigation = ET - precipitation - gwr;
        if (irrigation < 0) irrigation = 0;

        ((TextView)findViewById(R.id.txtPlants))
                .setText("Recommended irrigation:\n≈ "
                        + irrigation + " L / m²");
    }
}
