package com.androidarchitecture

import androidx.multidex.MultiDexApplication

class ArchitectureApplication : MultiDexApplication() {

    init {
        instance = this
    }

    companion object {
        private var instance: ArchitectureApplication? = null

        fun getContext() = instance?.applicationContext!!
    }
}
