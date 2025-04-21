package com.example.carrentaldemo.data.manager

import android.util.Log

// Sends over speed alert via AWS
class AWSManager : INotifyManager{
    override suspend fun sendOverSpeedAlert(vehicleId: String, speed: Int) {
        Log.d("AWSManager", "Sending over speed alert: $vehicleId @ $speed")
        //TODO("Add code to send to AWS")
    }
}