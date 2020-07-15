package com.androidarchitecture.utilities

import android.content.Context
import android.net.ConnectivityManager
import com.androidarchitecture.ArchitectureApplication
import com.androidarchitecture.domain.NetworkMonitor

class LiveConnectivityMonitor : NetworkMonitor {

    override fun isConnected(): Boolean {
        val cm =
            ArchitectureApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }
}
