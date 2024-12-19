package com.example.carpoolingapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DriverListActivity extends AppCompatActivity {

    private RecyclerView driverRecyclerView;
    private DriverAdapter driverAdapter;
    private ArrayList<Driver> driverList;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_list);

        driverRecyclerView = findViewById(R.id.driver_list_recyclerview);
        driverList = new ArrayList<>();
        dbHelper = new DatabaseHelper(this);

        // Initialize RecyclerView
        driverRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        driverAdapter = new DriverAdapter(driverList);
        driverRecyclerView.setAdapter(driverAdapter);

        fetchAvailableDrivers();

        driverAdapter.setOnItemClickListener(new DriverAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Driver selectedDriver = driverList.get(position);

                Toast.makeText(DriverListActivity.this, "Driver " + selectedDriver.getName() + " selected!", Toast.LENGTH_SHORT).show();

                Intent loadingIntent = new Intent(DriverListActivity.this, LoadingActivity.class);
                loadingIntent.putExtra("driverId", selectedDriver.getId());
                startActivity(loadingIntent);
            }
        });
    }

    private void fetchAvailableDrivers() {
        driverList.clear();
        driverList.addAll(dbHelper.getAvailableDrivers());
        driverAdapter.notifyDataSetChanged();
    }
}