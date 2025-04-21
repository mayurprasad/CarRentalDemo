package com.example.carrentaldemo.data.manager

// Chooses which notifier to use (Firebase or AWS) and sends alert
class NotifyManager(useFirebase: Boolean) {
    private val notifyManager: INotifyManager = if (useFirebase) {
        FirebaseManager()
    } else {
        AWSManager()
    }

    suspend fun notifySpeedAlert(vehicleId: String, speed: Int) {
        notifyManager.sendOverSpeedAlert(vehicleId, speed)
    }
}
