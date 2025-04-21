package com.example.carrentaldemo.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.carrentaldemo.R

// Used to create notifications for foreground service and over speed alerts
object NotificationUtil {

    // Notification shown when the monitoring service starts
    fun createForegroundNotification(context: Context): Notification {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                Constants.NOTIF_CHANNEL_ID,
                Constants.NOTIF_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = context.getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }

        return NotificationCompat.Builder(context, Constants.NOTIF_CHANNEL_ID)
            .setContentTitle("Speed Monitoring Active")
            .setContentText("Keeping track of driving speed.")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setOngoing(true)
            .build()
    }

    // Notification shown when user exceeds speed
    fun createOverSpeedNotification(context: Context, speed: Float): Notification {
        return NotificationCompat.Builder(context, Constants.NOTIF_CHANNEL_ID)
            .setContentTitle("Over Speeding Alert")
            .setContentText("You are driving at $speed. Please slow down.")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
    }
}
