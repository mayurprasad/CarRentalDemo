package com.example.carrentaldemo.service

import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.carrentaldemo.data.manager.CarSpeedManager
import com.example.carrentaldemo.data.manager.NotifyManager
import com.example.carrentaldemo.data.model.RentalSettings
import com.example.carrentaldemo.utils.NotificationUtil
import com.example.carrentaldemo.utils.Constants
import kotlinx.coroutines.*

// Background service that listens to speed and alerts if limit is exceeded
class SpeedMonitorService : Service() {

    companion object {
        private const val TAG: String = "SpeedMonitorService"
    }

    private var isMonitoringStarted = false
    private lateinit var rentalSettings: RentalSettings
    private lateinit var carSpeedManager: CarSpeedManager
    private lateinit var notifyManager: NotifyManager

    private val serviceJob = SupervisorJob()
    private val serviceScope = CoroutineScope(Dispatchers.IO + serviceJob)

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Service started")

        // Assuming we will get this values on rental start. Replace with dynamic values in Real App.
        rentalSettings = Constants.getDummyRentalSettings()

        notifyManager = NotifyManager(rentalSettings.useFirebase)
        carSpeedManager = CarSpeedManager(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (!isMonitoringStarted) {
            isMonitoringStarted = true

            // Start service with foreground notification
            startForeground(
                Constants.NOTIF_ID_FOREGROUND,
                NotificationUtil.createForegroundNotification(this)
            )

            // Start listening to speed changes
            carSpeedManager.registerSpeedListener { speed ->
                if (speed > rentalSettings.maxSpeedLimit) {
                    Log.w(TAG, "Speed exceeded! Notifying...")

                    serviceScope.launch {
                        notifyManager.notifySpeedAlert(rentalSettings.vehicleId, speed.toInt())
                    }

                    val notification = NotificationUtil.createOverSpeedNotification(this, speed)
                    val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                    manager.notify(Constants.NOTIF_ID_ALERT, notification)
                }
            }
        }

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel()
        carSpeedManager.unregisterListener()
        Log.d(TAG, "Service stopped and coroutines canceled")
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
