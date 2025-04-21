package com.example.carrentaldemo.utils

import com.example.carrentaldemo.data.model.RentalSettings

// Holds constant values used throughout the app
object Constants {
    const val NOTIF_CHANNEL_ID = "speed_monitor_channel"
    const val NOTIF_CHANNEL_NAME = "Speed Monitor"
    const val NOTIF_ID_FOREGROUND = 1
    const val NOTIF_ID_ALERT = 2

    // Assuming we will get this values on rental start. Fetched from API and stored locally.
    fun getDummyRentalSettings(): RentalSettings {
        return RentalSettings(
            vehicleId = "vehicle_101",
            rentalId = "rental_101",
            useFirebase = true,
            maxSpeedLimit = 80f
        )
    }
}