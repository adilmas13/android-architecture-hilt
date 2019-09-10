package com.shaikh.androidarchitecture

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    lateinit var data: LiveData<Boolean>
    

}