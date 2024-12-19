package com.example.carpoolingapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class RoleSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_selection);

        Button passengerButton = findViewById(R.id.passengerButton);
        Button driverButton = findViewById(R.id.driverButton);

        final String username = getIntent().getStringExtra("username");
        final String currentRole = getIntent().getStringExtra("role");

        // Set onClick listeners for both buttons
        passengerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //saveUserRole(username, "Passenger");
                startActivity(new Intent(RoleSelectionActivity.this, PassengerMapActivity.class));
                finish();
            }
        });

        driverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserRole(username,"Driver");
                Intent intent = new Intent(RoleSelectionActivity.this, DriverViewActivity.class);
                //intent.putExtra("driver_id", user_id);
                startActivity(intent);
            }
        });
    }

   private void saveUserRole(String username, String role) {
        SQLiteDatabase db = openOrCreateDatabase("CarpoolingUsers", MODE_PRIVATE, null);
        ContentValues contentValues = new ContentValues();
        contentValues.put("role", role); // Save the role
        ((android.database.sqlite.SQLiteDatabase) db).update("users", contentValues, "username = ?", new String[]{username});
    }
}