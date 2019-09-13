package com.shaikh.androidarchitecture.presentation

import androidx.lifecycle.ViewModelProvider


data class ViewModelCreator<M>(val type: Class<M>, val factory: ViewModelProvider.Factory? = null)