package com.example.carrentaldemo.data.manager

import android.car.Car
import android.car.hardware.CarPropertyValue
import android.car.hardware.property.CarPropertyManager
import android.content.Context
import android.util.Log
import kotlin.random.Random

// Wrapper around CarPropertyManager to listen to speed
class CarSpeedManager(private val context: Context) {

    companion object {
        private const val TAG: String = "CarSpeedManager"
    }

    private var car: Car? = null
    private var carPropertyManager: CarPropertyManager? = null

    // Registers for speed changes
    fun registerSpeedListener(callback: (Float) -> Unit) {

        car = Car.createCar(context)
        car?.connect()
        carPropertyManager = car?.getCarManager(Car.PROPERTY_SERVICE) as CarPropertyManager

        carPropertyManager?.registerCallback(object : CarPropertyManager.CarPropertyEventCallback {
            override fun onChangeEvent(value: CarPropertyValue<*>) {
                if (value.propertyId == VehiclePropertyIds.PERF_VEHICLE_SPEED) {
                    val speed = value.value as? Float ?: return
                    Log.d(TAG, "Speed update: $speed")
                    callback.invoke(speed)
                }
            }

            override fun onErrorEvent(propId: Int, zone: Int) {
                Log.e(TAG, "Car property error: $propId")
            }
        }, VehiclePropertyIds.PERF_VEHICLE_SPEED, CarPropertyManager.SENSOR_RATE_ONCHANGE)

        // Below is the dummy implementation to get the random speed and can be used to test the behaviour with dummy data on Emulator
//        val random = Random.nextInt(from = 70, until = 120)
//        callback.invoke(random.toFloat())
    }

    // Cleans up listeners and car connection
    fun unregisterListener() {
        Log.d(TAG, "Car disconnected and listener removed")
        carPropertyManager?.unregisterCallback(null)
        car?.disconnect()
        car = null
        carPropertyManager = null
    }
}
