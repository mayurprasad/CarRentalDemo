package com.example.carrentaldemo.data.manager

// Interface for notifying rental company about over speed
interface INotifyManager {
    suspend fun sendOverSpeedAlert(vehicleId: String, speed: Int)
}