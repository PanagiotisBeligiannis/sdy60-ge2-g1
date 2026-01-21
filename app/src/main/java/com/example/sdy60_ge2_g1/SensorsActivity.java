package com.example.sdy60_ge2_g1;

import android.hardware.*;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import java.util.Random;

public class SensorsActivity extends AppCompatActivity {

    private TextView txtRaw, txtCal;
    private SwitchCompat sw;

    // Calibration offsets (σταθερές βάσει σεναρίου)
    private static final float TEMP_OFFSET = +1.2f;
    private static final float HUM_OFFSET  = -2.5f;
    private static final float PRES_OFFSET = +4.0f;

    // Raw simulated values
    private float rawTemp, rawHum, rawPres;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_sensors);

        txtRaw = findViewById(R.id.txtRaw);
        txtCal = findViewById(R.id.txtCalibrated);
        sw = findViewById(R.id.switchCalibration);

        generateRawValues();
        updateUI(false);

        sw.setOnCheckedChangeListener((v, isChecked) ->
                updateUI(isChecked)
        );
    }

    private void generateRawValues() {
        Random r = new Random();

        rawTemp = 20 + r.nextFloat() * 10;
        rawHum  = 40 + r.nextFloat() * 40;
        rawPres = 990 + r.nextFloat() * 30;
    }

    private void updateUI(boolean calibrated) {

        txtRaw.setText(
                "RAW MEASUREMENTS\n" +
                        "Temperature: " + f(rawTemp) + " °C\n" +
                        "Humidity: " + f(rawHum) + " %\n" +
                        "Pressure: " + f(rawPres) + " hPa"
        );

        if (calibrated) {
            txtCal.setText(
                    "CALIBRATED MEASUREMENTS\n" +
                            "Temperature: " + f(rawTemp + TEMP_OFFSET) + " °C\n" +
                            "Humidity: " + f(rawHum + HUM_OFFSET) + " %\n" +
                            "Pressure: " + f(rawPres + PRES_OFFSET) + " hPa\n\n" +
                            "Calibration model: additive offset"
            );
        } else {
            txtCal.setText("Calibration disabled");
        }
    }

    private String f(float v) {
        return String.format("%.2f", v);
    }
}
