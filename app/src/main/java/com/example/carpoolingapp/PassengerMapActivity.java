package com.example.carpoolingapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class PassengerMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private EditText searchLocation;
    private Button selectDestination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_map);

        searchLocation = findViewById(R.id.search_location);
        selectDestination = findViewById(R.id.select_destination);

        // Initialize the map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        selectDestination.setOnClickListener(view -> {
            // Logic to handle destination selection and transition to next screen
            String location = searchLocation.getText().toString();
            // Search for location using Google Places API or custom logic
            // After location is selected, move to next screen with available drivers
            Intent intent = new Intent(PassengerMapActivity.this, DriverListActivity.class);
            intent.putExtra("destination", location);
            startActivity(intent);
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Set default map location
        LatLng defaultLocation = new LatLng(42.0, 21.0);  // Example coordinates
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 10));

        mMap.setOnMapClickListener(latLng -> {
            mMap.clear(); // Clear existing markers
            mMap.addMarker(new MarkerOptions().position(latLng).title("Selected Location"));
        });
    }
}
