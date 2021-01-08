package com.lucasdias.core.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class Connectivity(context: Context) {

    private var isDeviceJustStarted = true
    private val _mutableLiveData = MutableLiveData<Boolean>()
    val liveData: LiveData<Boolean> = _mutableLiveData

    init {
        registerNetworkCallback(connectivityManager = getConnectivityManager(context))
    }

    private fun getConnectivityManager(context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
    }

    private fun registerNetworkCallback(connectivityManager: ConnectivityManager) {
        connectivityManager.registerDefaultNetworkCallback(
            ConnectivityCallback { isConnected: Boolean ->
                notifyConnectedState(isConnected)
            }
        )
    }

    private fun notifyConnectedState(isConnected: Boolean) {
        if (isDeviceJustStarted && isConnected) return

        isDeviceJustStarted = false
        _mutableLiveData.postValue(isConnected)
    }

    class ConnectivityCallback(val notifyConnectedState: (Boolean) -> Unit) :
        ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            notifyConnectedState(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            notifyConnectedState(false)
        }
    }
}
