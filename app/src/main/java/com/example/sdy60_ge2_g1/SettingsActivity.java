package com.example.sdy60_ge2_g1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_settings);

        Spinner genderSpinner = findViewById(R.id.spinnerGender);
        EditText weightEdit = findViewById(R.id.editWeight);
        Button saveBtn = findViewById(R.id.btnSave);

        saveBtn.setOnClickListener(v -> {
            SharedPreferences prefs =
                    getSharedPreferences("user_data", MODE_PRIVATE);

            prefs.edit()
                    .putString("gender", genderSpinner.getSelectedItem().toString())
                    .putFloat("weight",
                            Float.parseFloat(weightEdit.getText().toString()))
                    .apply();

            finish();
        });
    }
}
