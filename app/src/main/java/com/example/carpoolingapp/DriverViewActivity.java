package com.example.carpoolingapp;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DriverViewActivity extends AppCompatActivity {

    private TextView driverRatingText;
    private EditText vehicleInfoInput, activeTimeInput;
    private Button saveInfoButton, ratePassengerButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_view);

        // Initialize views
        driverRatingText = findViewById(R.id.driverRatingText);
        vehicleInfoInput = findViewById(R.id.vehicleInfoInput);
        activeTimeInput = findViewById(R.id.activeTimeInput);
        saveInfoButton = findViewById(R.id.saveInfoButton);

        databaseHelper = new DatabaseHelper(this);

        // Retrieve driver ID from intent (assuming it's passed from the previous activity)
        int driverId = getIntent().getIntExtra("driver_id", -1);
        if (driverId != -1) {
            double rating = 4.5;
            driverRatingText.setText("Your Rating: " + rating);
        }

        saveInfoButton.setOnClickListener(view -> {
            String vehicleInfo = vehicleInfoInput.getText().toString();
            String activeTime = activeTimeInput.getText().toString();

            if (!vehicleInfo.isEmpty() && !activeTime.isEmpty()) {
                boolean success = databaseHelper.saveDriverInfo(driverId, vehicleInfo, activeTime);
                if (success) {
                    Toast.makeText(DriverViewActivity.this, "Information saved successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DriverViewActivity.this, "Failed to save information", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(DriverViewActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
