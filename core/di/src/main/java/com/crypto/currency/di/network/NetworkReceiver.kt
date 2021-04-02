package com.crypto.currency.di.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NetworkReceiver : BroadcastReceiver() {

    private val _wifiAvailable = MutableLiveData<Boolean>()
    val wifiAvailable: LiveData<Boolean> = _wifiAvailable


    override fun onReceive(context: Context, intent: Intent) {
        val conn = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities: NetworkCapabilities? =
            conn.getNetworkCapabilities(conn.activeNetwork)
        val newStatus =
            networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
        if (newStatus != _wifiAvailable.value) {
            _wifiAvailable.value = newStatus
        }

    }
}