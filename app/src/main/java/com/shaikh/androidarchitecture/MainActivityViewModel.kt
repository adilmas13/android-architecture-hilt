package com.shaikh.androidarchitecture

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    var data: MutableLiveData<MutableList<Users>> = MutableLiveData()

    var loading: MutableLiveData<Boolean> = MutableLiveData()

    private val repository = UsersRepository()

    fun getUsers() {
        loading.value = true
        repository
            .getUsers(this::onApiSuccess, this::onApiFailure)
    }

    private fun onApiSuccess(users: List<Users>) {
        data.value = users.toMutableList()
        loading.value = false
    }

    private fun onApiFailure(error: Exception) {
        loading.value = false
    }
}