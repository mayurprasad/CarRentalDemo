package com.example.carrentaldemo.data.model

// Holds max allowed speed for a specific car rental
data class RentalSettings(
    val vehicleId: String,
    val rentalId: String,
    val useFirebase: Boolean,   // True = Firebase, False = AWS
    val maxSpeedLimit: Float
)
