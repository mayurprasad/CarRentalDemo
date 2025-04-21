package com.example.carrentaldemo.data.manager

import android.util.Log

// Sends over speed alert via Firebase
class FirebaseManager : INotifyManager {
    override suspend fun sendOverSpeedAlert(vehicleId: String, speed: Int) {
        Log.d("FirebaseManager", "Sending over speed alert: $vehicleId @ $speed")
        //TODO("Add code to send to Firebase")
    }
}