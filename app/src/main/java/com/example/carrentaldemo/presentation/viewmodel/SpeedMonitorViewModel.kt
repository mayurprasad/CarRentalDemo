package com.example.carrentaldemo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel to notify over speed alert on view.
 */
class SpeedMonitorViewModel: ViewModel() {
    private val _alertOverSpeed = MutableLiveData<Boolean>()
    val alertOverSpeed: LiveData<Boolean> = _alertOverSpeed

    fun notifySpeedAlert(isOverSpeed: Boolean) {
        _alertOverSpeed.postValue(isOverSpeed)
    }
}