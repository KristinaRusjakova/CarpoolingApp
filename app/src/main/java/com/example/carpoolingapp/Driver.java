package com.example.carpoolingapp;

public class Driver extends User {
    private String vehicleType;
    private String workingHours;
    private double pricePerRide;
    private double averageRatingFromPassengers;

    // Constructor
    public Driver(int userId, String username, String email, String password, String vehicleType, String workingHours, double pricePerRide, double averageRatingFromPassengers) {
        super(userId, username, email, password, "driver");
        this.vehicleType = vehicleType;
        this.workingHours = workingHours;
        this.pricePerRide = pricePerRide;
        this.averageRatingFromPassengers = averageRatingFromPassengers;
    }

    // Getters and Setters
    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public double getPrice() {
        return pricePerRide;
    }

    public void setPrice(double pricePerRide) {
        this.pricePerRide = pricePerRide;
    }

    public double getRating() {
        return averageRatingFromPassengers;
    }

    public void setRating(double averageRatingFromPassengers) {
        this.averageRatingFromPassengers = averageRatingFromPassengers;
    }
}
