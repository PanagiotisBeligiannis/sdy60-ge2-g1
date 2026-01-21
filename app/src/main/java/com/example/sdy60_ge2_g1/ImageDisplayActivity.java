package com.example.sdy60_ge2_g1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ImageDisplayActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView tvCountdown;
    private Handler handler = new Handler();
    private int remainingTime = 10; // seconds
    private Runnable countdownRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);

        imageView = findViewById(R.id.imageView);
        tvCountdown = findViewById(R.id.tvCountdown);

        // Get image name from intent
        Intent intent = getIntent();
        String imageName = intent.getStringExtra("image_name");
        int duration = intent.getIntExtra("image_duration", 10000);

        // Load the appropriate image
        loadImage(imageName);

        // Start countdown
        startCountdown(duration);
    }

    private void loadImage(String imageName) {
        int resourceId;

        if ("tips-for-you".equals(imageName)) {
            resourceId = R.drawable.tips_for_you;
        } else if ("tips-for-the-plants".equals(imageName)) {
            resourceId = R.drawable.tips_for_the_plants;
        } else {
            // Default image if not found
            resourceId = R.drawable.tips_for_you;
        }

        imageView.setImageResource(resourceId);
    }

    private void startCountdown(int duration) {
        final int totalSeconds = duration / 1000;
        remainingTime = totalSeconds;

        countdownRunnable = new Runnable() {
            @Override
            public void run() {
                if (remainingTime > 0) {
                    tvCountdown.setText("Returning in " + remainingTime + " seconds...");
                    remainingTime--;
                    handler.postDelayed(this, 1000);
                } else {
                    // Return to main activity
                    finish();
                }
            }
        };

        // Start the countdown
        handler.post(countdownRunnable);

        // Also set a delayed action to return automatically after the specified duration
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isFinishing()) {
                    finish();
                }
            }
        }, duration);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove any pending callbacks to prevent memory leaks
        if (handler != null && countdownRunnable != null) {
            handler.removeCallbacks(countdownRunnable);
        }
    }

    @Override
    public void onBackPressed() {
        // Allow user to go back manually before the timer expires
        super.onBackPressed();
        if (handler != null && countdownRunnable != null) {
            handler.removeCallbacks(countdownRunnable);
        }
    }
}