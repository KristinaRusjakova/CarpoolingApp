package com.example.carpoolingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "CarpoolApp.db";
    private static final int DATABASE_VERSION = 1;

    // Table: Users
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_ROLE = "role";

    // Additional columns for passengers
    private static final String COLUMN_RATING_FROM_DRIVERS = "rating_from_drivers";
    private static final String COLUMN_TOTAL_RIDES = "total_rides";

    // Additional columns for drivers
    private static final String COLUMN_VEHICLE_TYPE = "vehicle_type";
    private static final String COLUMN_WORKING_HOURS = "working_hours";
    private static final String COLUMN_PRICE_PER_RIDE = "price_per_ride";
    private static final String COLUMN_AVERAGE_RATING_FROM_PASSENGERS = "average_rating_from_passengers";

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create users table
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + " ("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USERNAME + " TEXT NOT NULL, "
                + COLUMN_EMAIL + " TEXT NOT NULL UNIQUE, "
                + COLUMN_PASSWORD + " TEXT NOT NULL, "
                + COLUMN_ROLE + " TEXT NOT NULL, "
                + COLUMN_RATING_FROM_DRIVERS + " REAL DEFAULT 0, "
                + COLUMN_TOTAL_RIDES + " INTEGER DEFAULT 0, "
                + COLUMN_VEHICLE_TYPE + " TEXT, "
                + COLUMN_WORKING_HOURS + " TEXT, "
                + COLUMN_PRICE_PER_RIDE + " REAL DEFAULT 0, "
                + COLUMN_AVERAGE_RATING_FROM_PASSENGERS + " REAL DEFAULT 0"
                + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public long insertUser(String username, String email, String password, String role) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_ROLE, role);

        return db.insert(TABLE_USERS, null, values);
    }
    public int updatePassengerDetails(int userId, double ratingFromDrivers, int totalRides) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_RATING_FROM_DRIVERS, ratingFromDrivers);
        values.put(COLUMN_TOTAL_RIDES, totalRides);

        return db.update(TABLE_USERS, values, COLUMN_USER_ID + " = ?", new String[]{String.valueOf(userId)});
    }

    public int updateDriverDetails(int userId, String vehicleType, String workingHours, double pricePerRide, double averageRating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_VEHICLE_TYPE, vehicleType);
        values.put(COLUMN_WORKING_HOURS, workingHours);
        values.put(COLUMN_PRICE_PER_RIDE, pricePerRide);
        values.put(COLUMN_AVERAGE_RATING_FROM_PASSENGERS, averageRating);

        return db.update(TABLE_USERS, values, COLUMN_USER_ID + " = ?", new String[]{String.valueOf(userId)});
    }

    // Retrieve a user by ID
    public Cursor getUserById(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USER_ID + " = ?", new String[]{String.valueOf(userId)});
    }

    public Cursor getUserByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " = ?", new String[]{email});
    }

    public boolean authenticateUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ?", new String[]{email, password});

        boolean isAuthenticated = cursor.getCount() > 0;
        cursor.close();
        return isAuthenticated;
    }

    public int deleteUser(int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_USERS, COLUMN_USER_ID + " = ?", new String[]{String.valueOf(userId)});
    }

    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_USERS, null);
    }

    public ArrayList<Driver> getAvailableDrivers() {
        ArrayList<Driver> drivers = new ArrayList<>();
        drivers.add(new Driver(1, "John Doe", "Sedan", "123", "SUV", "8am - 11pm", 100, 4.5));
        drivers.add(new Driver(2, "Jane Smith", "SUV", "456", "Honda Civic", "11am - 4pm", 150, 3.7));
        return drivers;
    }

    public boolean saveDriverRating(int driverId, float rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_AVERAGE_RATING_FROM_PASSENGERS, rating);

        int rowsAffected = db.update(TABLE_USERS, values, "id=?", new String[]{String.valueOf(driverId)});
        return rowsAffected > 0;
    }

    public boolean saveDriverInfo(int driverId, String vehicleInfo, String activeTime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, driverId);
        values.put(COLUMN_VEHICLE_TYPE, vehicleInfo);
        values.put(COLUMN_WORKING_HOURS, activeTime);

        int rowsAffected = db.update(TABLE_USERS, values, "id=?", new String[]{String.valueOf(driverId)});
        return rowsAffected > 0;
    }

    public Boolean checkEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " = ?";
        Cursor cursor = db.rawQuery(query, new String[] { email });

        if (cursor != null && cursor.getCount() > 0) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

}