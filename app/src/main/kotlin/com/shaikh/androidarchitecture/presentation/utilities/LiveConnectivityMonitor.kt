package com.shaikh.androidarchitecture.presentation.utilities

import android.content.Context
import android.net.ConnectivityManager
import com.shaikh.androidarchitecture.ArchitectureApplication
import com.shaikh.androidarchitecture.data.NetworkMonitor


class LiveConnectivityMonitor : NetworkMonitor {

    override fun isConnected(): Boolean {
        val cm =
            ArchitectureApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }
}