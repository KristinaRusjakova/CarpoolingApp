package com.example.carpoolingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RideActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private Button submitRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride);

        ratingBar = findViewById(R.id.rating_bar);
        submitRating = findViewById(R.id.submit_rating);

        submitRating.setOnClickListener(view -> {
            float rating = ratingBar.getRating();
            if (rating > 0) {
                Toast.makeText(RideActivity.this, "Thank you for rating!", Toast.LENGTH_SHORT).show();
                // sqlite logic
                Intent intent = new Intent(RideActivity.this, PassengerMapActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(RideActivity.this, "Please provide a rating", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
